package com.grzybowski.nbpapi.controllers;

import com.grzybowski.nbpapi.domain.ExchangeRateTableA;
import com.grzybowski.nbpapi.services.ExchangeRateAssembler;
import com.grzybowski.nbpapi.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(path = "/exchanges-rates")
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangesService;

    @RequestMapping("/{code}/{date}")
    public ResponseEntity<ExchangeRateAssembler> getExchangeRate(@PathVariable("code") String code,
                                                                 @PathVariable("date") String date){

        Optional<ExchangeRateTableA> exchangeRateData = exchangesService
                .getExchangeRateValues(LocalDate.parse(date), code);

        return exchangeRateData
                .map(value -> ResponseEntity
                        .ok(ExchangeRateAssembler
                                .assembleExchangeRateDTO()
                                .apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());

    }
}
