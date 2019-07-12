package com.javatechie.camelrestdsl;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonProcessor implements Processor {

    @Autowired
    private PersonService service;

    @Override
    public void process(Exchange exchange) {
        service.addPerson(exchange.getIn().getBody(Person.class));
    }
}
