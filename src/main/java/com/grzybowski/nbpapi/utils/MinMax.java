package com.grzybowski.nbpapi.utils;

import com.grzybowski.nbpapi.domain.ExchangeRateParametersA;
import com.grzybowski.nbpapi.domain.ExchangeRateTableA;

public class MinMax {
    public static ExchangeRateParametersA min(ExchangeRateTableA exchangeRateData) {
        ExchangeRateParametersA minRateValue = new ExchangeRateParametersA();

        minRateValue.setExchangeRate(exchangeRateData.getRates().get(0).getExchangeRate());


        for(ExchangeRateParametersA exchangeRateParameters : exchangeRateData.getRates()) {
            if (exchangeRateParameters.getExchangeRate() < minRateValue.getExchangeRate())
                minRateValue = exchangeRateParameters;
        }

        return minRateValue;
    }

    public static ExchangeRateParametersA max(ExchangeRateTableA exchangeRateData) {
        ExchangeRateParametersA maxRateValue = new ExchangeRateParametersA();

        maxRateValue.setExchangeRate(exchangeRateData.getRates().get(0).getExchangeRate());


        for(ExchangeRateParametersA exchangeRateParameters : exchangeRateData.getRates()) {
            if (exchangeRateParameters.getExchangeRate() > maxRateValue.getExchangeRate())
                maxRateValue = exchangeRateParameters;
        }

        return maxRateValue;
    }
}
