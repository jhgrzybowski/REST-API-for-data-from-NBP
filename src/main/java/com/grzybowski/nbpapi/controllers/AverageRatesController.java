package com.grzybowski.nbpapi.controllers;

import com.grzybowski.nbpapi.domain.ExchangeRateTableA;
import com.grzybowski.nbpapi.services.AverageRatesAssembler;
import com.grzybowski.nbpapi.services.AverageRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("average-exchange-rates")
public class AverageRatesController {

    @Autowired
    AverageRatesService averageRatesService;

    @RequestMapping("/{code}/{quotations}")
    public ResponseEntity<AverageRatesAssembler> getAverageExchangeRates(@PathVariable("code") String code,
                                                                         @PathVariable("quotations") int quotations) {

        Optional<ExchangeRateTableA> exchangeRateData = averageRatesService.getAverageRate(quotations, code);

        return exchangeRateData
                .map(value -> ResponseEntity
                        .ok(AverageRatesAssembler
                                .assembleAverageRatesDTO()
                                .apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }
}
