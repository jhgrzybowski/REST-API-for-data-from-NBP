package com.grzybowski.nbpapi.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeRateTableA extends ExchangeRate{

    public ExchangeRateTableA() {
        super();
    }

    @JsonProperty("rates")
    private List<ExchangeRateParametersA> rates;

}
