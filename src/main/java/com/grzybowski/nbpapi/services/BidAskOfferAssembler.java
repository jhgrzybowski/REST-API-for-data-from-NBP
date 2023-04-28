package com.grzybowski.nbpapi.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grzybowski.nbpapi.domain.ExchangeRateTableA;
import com.grzybowski.nbpapi.domain.ExchangeRateTableC;
import lombok.Builder;

import java.util.function.Function;

@Builder
public class BidAskOfferAssembler {

    @JsonProperty
    private final float highestOffer;

    public static Function<ExchangeRateTableC, BidAskOfferAssembler> assembleOfferDTO() {
        return exchangeRateData -> BidAskOfferAssembler.builder()
                .highestOffer(Math.abs(
                        exchangeRateData.getRates().get(0).getAsk() - exchangeRateData.getRates().get(0).getBid()
                )).build();
    }

}
