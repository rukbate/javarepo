package com.scb.app.service;

import com.scb.app.exception.InstrumentException;
import com.scb.app.exception.InvalidMappingException;
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

    public AggregateService(List<Rule> rules) {
        this.rules.addAll(rules);
    }

    @Override
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public Instrument publish(Instrument instrument) throws InstrumentException {
        InstrumentType instrumentType = instrument.getType();
        if (!instrumentType.isExchange()) {
            throw new UnknownExchangeException();
        }

        String mappingValue = instrument.getMappingValue();
        if(mappingValue == null) {
            throw new InvalidMappingException();
        }

        List<Instrument> existingInstruments = instruments.stream()
                .filter(ins -> ins.match(mappingValue)).collect(Collectors.toList());

        this.addInstrument(instrument);

        InstrumentBuilder builder = new StandardInstrumentBuilder()
                .withField(InstrumentFields.CODE, instrument.getMappingValue());

        rules.forEach(rule -> rule.apply(instrument, existingInstruments, builder));
        return builder.build();
    }

    private void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }
}
