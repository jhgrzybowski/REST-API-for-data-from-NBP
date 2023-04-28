package com.grzybowski.nbpapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ExchangeRateParametersA extends ExchangeRateParameters {
    public ExchangeRateParametersA() {
        super();
    }

    @JsonProperty("mid")
    private float exchangeRate;
}
