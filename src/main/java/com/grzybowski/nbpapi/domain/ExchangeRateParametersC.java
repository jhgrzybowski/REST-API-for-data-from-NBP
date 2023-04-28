package com.grzybowski.nbpapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ExchangeRateParametersC extends ExchangeRateParameters{

    public ExchangeRateParametersC() {
        super();
    }

    @JsonProperty("bid")
    private float bid;

    @JsonProperty("ask")
    private float ask;
}
