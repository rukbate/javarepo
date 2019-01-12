package com.scb.app;

import com.scb.app.exception.InstrumentException;
import com.scb.app.instrument.builder.LmeInstrumentBuilder;
import com.scb.app.instrument.builder.PrimeInstrumentBuilder;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.rule.*;
import com.scb.app.service.AggregateService;
import com.scb.app.service.InstrumentService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main( String[] args ) throws InstrumentException {
        Instrument lmeInstrument = new LmeInstrumentBuilder()
                .withCode("PB_03_2018")
                .withLastTradingDate("15-03-2018")
                .withDeliveryDate("17-03-2018")
                .withMarket("LME_PB")
                .withLabel("Lead 13 March 2018")
                .build();

        Instrument primeInstrument = new PrimeInstrumentBuilder()
                .withCode("PRIME_PB_03_2018")
                .withExchangeCode("PB_03_2018")
                .withLastTradingDate("14-03-2018")
                .withDeliveryDate("18-03-2018")
                .withMarket("LME_PB")
                .withLabel("Lead 13 March 2018")
                .withTradable("FALSE")
                .build();

        Rule lastTradingDateAndDeliveryDateRule = new LastTradingDateAndDeliveryDateRule();
        Rule tradableRule = new TradableRule();
        Rule marketRule = new MarketRule();
        Rule defaultRule = new DefaultRule();

        List<Instrument> instruments = new ArrayList<>(8);
        instruments.add(lmeInstrument);

        List<Rule> rules = new LinkedList<>();
        rules.add(lastTradingDateAndDeliveryDateRule);
        rules.add(tradableRule);
        rules.add(marketRule);

        InstrumentService engine = new AggregateService(rules, instruments);
        engine.addInstrument(primeInstrument);
        engine.addRule(defaultRule);

        System.out.println("LME publishing PB_03_2018");
        System.out.println(engine.publish("LME", "PB_03_2018"));

        System.out.println("PRIME publishing PB_03_2018");
        System.out.println(engine.publish("PRIME", "PB_03_2018"));
    }
}
