package com.grzybowski.nbpapi.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grzybowski.nbpapi.domain.ExchangeRateTableA;
import lombok.Builder;

import java.util.function.Function;

@Builder
public class AverageRatesAssembler {

    @JsonProperty("minRateValue")
    private final float minRateValue;

    @JsonProperty("maxRateValue")
    private final float maxRateValue;

    public static Function<ExchangeRateTableA, AverageRatesAssembler> assembleAverageRatesDTO() {
        return exchangeRateData -> AverageRatesAssembler.builder()
                .maxRateValue(exchangeRateData.getRates().get(0).getExchangeRate())
                .minRateValue(exchangeRateData.getRates().get(1).getExchangeRate())
                .build();
    }

}
