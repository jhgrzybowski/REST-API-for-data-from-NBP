package com.grzybowski.nbpapi.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grzybowski.nbpapi.domain.ExchangeRateTableA;
import lombok.Builder;

import java.util.function.Function;

@Builder
public class ExchangeRateAssembler {

    @JsonProperty
    private final float exchangeRate;

    public static Function<ExchangeRateTableA, ExchangeRateAssembler> assembleExchangeRateDTO() {
        return exchangeRateData -> ExchangeRateAssembler.builder()
                .exchangeRate(exchangeRateData.getRates().get(0).getExchangeRate())
                .build();
    }
}
