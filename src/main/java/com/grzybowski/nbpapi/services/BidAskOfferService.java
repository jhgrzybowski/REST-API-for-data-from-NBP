package com.grzybowski.nbpapi.services;

import com.grzybowski.nbpapi.domain.ExchangeRateParametersC;
import com.grzybowski.nbpapi.domain.ExchangeRateTableC;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("bid-ask-offer")
public class BidAskOfferService {
    private final RestTemplate restTemplate;

    public BidAskOfferService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // highest offer is the major difference between the buy and ask rate
    // from the maximum of 255 quotations
    public Optional<ExchangeRateTableC> getHighestOffer(int quotationsNumber, String exchangeRateCode) {
        if(quotationsNumber <= 255) {

        String queryUrl = String.format("http://api.nbp.pl/api/exchangerates/rates/C/%s/last/%d",
                exchangeRateCode,
                quotationsNumber);

        try {

            ResponseEntity<ExchangeRateTableC> responseEntity = restTemplate.getForEntity(queryUrl, ExchangeRateTableC.class);

            ExchangeRateTableC exchangeRateData = responseEntity.getBody();

            if(exchangeRateData != null) exchangeRateData.setRates(List.of(Objects.requireNonNull(getHighestOffer(exchangeRateData))));

            return Optional.of(exchangeRateData);
        } catch (HttpClientErrorException exception) {
            return Optional.empty();
        }

        } else return Optional.empty();
    }

    private ExchangeRateParametersC getHighestOffer(ExchangeRateTableC exchangeRateData) {

        float highestOffer = 0.0f;
        ExchangeRateParametersC resultParameters = new ExchangeRateParametersC();

        assert exchangeRateData != null;
            for(ExchangeRateParametersC parameters : exchangeRateData.getRates()) {

                float offer = Math.abs(parameters.getAsk() - parameters.getBid());

                if(offer >= highestOffer) {
                    highestOffer = offer;
                    resultParameters = parameters;
                }
            }
            return resultParameters;
    }

}
