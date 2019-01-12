package com.scb.app;

import com.scb.app.exception.InstrumentException;
import com.scb.app.instrument.builder.LmeInstrumentBuilder;
import com.scb.app.instrument.builder.PrimeInstrumentBuilder;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.rule.*;
import com.scb.app.service.AggregateService;
import com.scb.app.service.InstrumentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main( String[] args ) throws InstrumentException {
        Instrument lmeInstrument = new LmeInstrumentBuilder()
                .withCode("PB_03_2018")
                .withLastTradingDate(LocalDate.of(2018, 3, 15))
                .withDeliveryDate(LocalDate.of(2018, 3, 17))
                .withMarket("LME_PB")
                .withLabel("Lead 13 March 2018")
                .build();

        Instrument primeInstrument = new PrimeInstrumentBuilder()
                .withCode("PRIME_PB_03_2018")
                .withExchangeCode("PB_03_2018")
                .withLastTradingDate(LocalDate.of(2018, 3, 14))
                .withDeliveryDate(LocalDate.of(2018, 3, 18))
                .withMarket("LME_PB")
                .withLabel("Lead 13 March 2018")
                .withTradable(false)
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
