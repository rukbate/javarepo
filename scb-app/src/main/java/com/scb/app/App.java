package com.scb.app;

import com.scb.app.exception.InstrumentException;
import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.builder.LmeInstrumentBuilder;
import com.scb.app.instrument.builder.PrimeInstrumentBuilder;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.rule.*;
import com.scb.app.service.AggregateService;
import com.scb.app.service.InstrumentService;

import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main( String[] args ) throws InstrumentException {
        Instrument lmeInstrument = new LmeInstrumentBuilder()
                .withField(InstrumentFields.CODE, "PB_03_2018")
                .withField(InstrumentFields.LAST_TRADING_DATE, "15-03-2018")
                .withField(InstrumentFields.DELIVERY_DATE, "17-03-2018")
                .withField(InstrumentFields.MARKET, "LME_PB")
                .withField(InstrumentFields.LABEL, "Lead 13 March 2018")
                .build();

        Instrument primeInstrument = new PrimeInstrumentBuilder()
                .withField(InstrumentFields.CODE, "PRIME_PB_03_2018")
                .withField(InstrumentFields.EXCHANGE_CODE, "PB_03_2018")
                .withField(InstrumentFields.LAST_TRADING_DATE, "14-03-2018")
                .withField(InstrumentFields.DELIVERY_DATE, "18-03-2018")
                .withField(InstrumentFields.MARKET, "LME_PB")
                .withField(InstrumentFields.LABEL, "Lead 13 March 2018")
                .withField(InstrumentFields.TRADABLE, "FALSE")
                .build();

        Rule lastTradingDateAndDeliveryDateRule = new LastTradingDateAndDeliveryDateRule();
        Rule tradableRule = new TradableRule();
        Rule marketRule = new MarketRule();
        Rule defaultRule = new DefaultRule();

        List<Rule> rules = new LinkedList<>();
        rules.add(lastTradingDateAndDeliveryDateRule);
        rules.add(tradableRule);
        rules.add(marketRule);
        rules.add(defaultRule);

        InstrumentService service = new AggregateService(rules);

        System.out.println("LME publishing PB_03_2018");
        System.out.println(service.publish(lmeInstrument));

        System.out.println("PRIME publishing PB_03_2018");
        System.out.println(service.publish(primeInstrument));
    }
}
