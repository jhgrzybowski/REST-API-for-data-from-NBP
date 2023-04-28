package com.grzybowski.nbpapi.services;

import com.grzybowski.nbpapi.domain.ExchangeRateParametersA;
import com.grzybowski.nbpapi.domain.ExchangeRateTableA;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.grzybowski.nbpapi.utils.MinMax;

import java.util.List;
import java.util.Optional;

import static com.grzybowski.nbpapi.utils.MinMax.max;
import static com.grzybowski.nbpapi.utils.MinMax.min;

@Service("average-exchange-rates")
public class AverageRatesService {

    private final RestTemplate restTemplate;
    public AverageRatesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<ExchangeRateTableA> getAverageRate(int quotationsNumber, String exchangeRateCode) {

        if(quotationsNumber <= 255) {

            String queryUrl = String.format("http://api.nbp.pl/api/exchangerates/rates/A/%s/last/%d",
                    exchangeRateCode,
                    quotationsNumber);

            try {
                ResponseEntity<ExchangeRateTableA> responseEntity = restTemplate.getForEntity(queryUrl, ExchangeRateTableA.class);

                ExchangeRateTableA exchangeRateData = responseEntity.getBody();
                assert exchangeRateData != null;
                exchangeRateData.setRates(getMinMaxAverageRates(exchangeRateData));

                return Optional.of(exchangeRateData);
            } catch (HttpClientErrorException exception) {
                return Optional.empty();
            }

        } else return Optional.empty();

    }

    private List<ExchangeRateParametersA> getMinMaxAverageRates(ExchangeRateTableA exchangeRateData) {

        ExchangeRateParametersA minRateValue = new ExchangeRateParametersA();
        ExchangeRateParametersA maxRateValue = new ExchangeRateParametersA();

        minRateValue.setExchangeRate(exchangeRateData.getRates().get(0).getExchangeRate());
        maxRateValue.setExchangeRate(exchangeRateData.getRates().get(0).getExchangeRate());

        minRateValue = min(exchangeRateData);
        maxRateValue = max(exchangeRateData);

        return List.of(minRateValue, maxRateValue);
    }

}
