package com.grzybowski.nbpapi.controllers;

import com.grzybowski.nbpapi.domain.ExchangeRateTableC;
import com.grzybowski.nbpapi.services.BidAskOfferAssembler;
import com.grzybowski.nbpapi.services.BidAskOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("bid-ask-offer")
public class BidAskOfferController {

    @Autowired
    BidAskOfferService bidAskOfferService;

    @RequestMapping("/{code}/{quotations}")
    public ResponseEntity<BidAskOfferAssembler> getHighestOffer(@PathVariable("code") String code,
                                                                @PathVariable("quotations") int quotations) {
        Optional<ExchangeRateTableC> exchangeRateData = bidAskOfferService.getHighestOffer(quotations, code);

        return exchangeRateData.map(
                value -> ResponseEntity
                        .ok(BidAskOfferAssembler
                                .assembleOfferDTO()
                                .apply(value))
        ).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }

}
