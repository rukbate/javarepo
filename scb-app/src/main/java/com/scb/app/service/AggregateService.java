package com.scb.app.service;

import com.scb.app.exception.InstrumentException;
import com.scb.app.exception.NoMatchedInstrumentException;
import com.scb.app.exception.UnknownExchangeException;
import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.InstrumentType;
import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.builder.StandardInstrumentBuilder;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.rule.Rule;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class AggregateService implements InstrumentService {

    private Set<Instrument> instruments = ConcurrentHashMap.newKeySet(10);
    private List<Rule> rules = new CopyOnWriteArrayList<>();

    public AggregateService(List<Rule> rules, List<Instrument> instruments) {
        this.instruments.addAll(instruments);
        this.rules.addAll(rules);
    }

    @Override
    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    @Override
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public Instrument publish(String exchange, String code) throws InstrumentException {
        InstrumentType instrumentType = InstrumentType.fromName(exchange);
        if(instrumentType == null) {
            throw new UnknownExchangeException();
        }

        List<Instrument> matchInstruments = instruments.stream()
                .filter(instrument -> instrument.match(code)).collect(Collectors.toList());

        if (matchInstruments.isEmpty()) {
            throw new NoMatchedInstrumentException();
        }

        InstrumentBuilder builder = new StandardInstrumentBuilder().withField(InstrumentFields.CODE, code);

        rules.forEach(rule -> rule.apply(exchange, matchInstruments, builder));
        return builder.build();
    }
}
