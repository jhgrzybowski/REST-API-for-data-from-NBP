package com.grzybowski.nbpapi.services;

import com.grzybowski.nbpapi.domain.ExchangeRateTableA;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Optional;

@Service("exchanges-rates")
public class ExchangeRateService {
    private final RestTemplate restTemplate;

    public ExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<ExchangeRateTableA> getExchangeRateValues(LocalDate date, String exchangeRateCode) {

        if(!isWeekend(date)) {
            String queryUrl = String.format(
                    "http://api.nbp.pl/api/exchangerates/rates/A/%s/%s",
                    exchangeRateCode,
                    date);

            try {
                ResponseEntity<ExchangeRateTableA> responseEntity = restTemplate.getForEntity(queryUrl, ExchangeRateTableA.class);

                return Optional.ofNullable(responseEntity.getBody());

            } catch (HttpClientErrorException exception) {
                return Optional.empty();
            }

        } else return Optional.empty();
    }

    private static boolean isWeekend(final LocalDate ld)
    {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }

}
