package com.icu;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.Currency;

import java.math.BigDecimal;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");
        Currency cny = Currency.getInstance("CNY");
        Currency jpy = Currency.getInstance("JPY");
        Currency krw = Currency.getInstance("KRW");
        Currency ves = Currency.getInstance("VES");

        System.out.println(usd.getDefaultFractionDigits());
        System.out.println(eur.getDefaultFractionDigits());
        System.out.println(cny.getDefaultFractionDigits());
        System.out.println(jpy.getDefaultFractionDigits());
        System.out.println(krw.getDefaultFractionDigits());
        System.out.println(ves.getDefaultFractionDigits());

        BigDecimal num = new BigDecimal("100010000");
        NumberFormat formatter =
                new RuleBasedNumberFormat(Locale.ENGLISH, RuleBasedNumberFormat.SPELLOUT);
        String result = formatter.format(num);
        System.out.println(result);
    }
}
