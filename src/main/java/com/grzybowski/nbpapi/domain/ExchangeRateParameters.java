package com.grzybowski.nbpapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public abstract class ExchangeRateParameters {
    @JsonProperty("no")
    private String no;

    @JsonProperty("effectiveDate")
    private LocalDate effectiveDate;

}
