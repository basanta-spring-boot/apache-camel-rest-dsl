package com.javatechie.camelrestdsl;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CamelResource extends RouteBuilder {

    @Autowired
    private PersonService service;

    @BeanInject
    private PersonProcessor processor;

    @Override
    public void configure() {
        restConfiguration().component("servlet").port(9090).host("localhost").bindingMode(RestBindingMode.json);

        rest().get("/getText").produces(MediaType.APPLICATION_JSON_VALUE)
                .route().setBody(constant("Hello-world"));

        rest().get("/getAll").produces(MediaType.APPLICATION_JSON_VALUE).type(Person.class)
                .route().setBody(() -> service.getPersons());

        rest().post("/save").consumes(MediaType.APPLICATION_JSON_VALUE).type(Person.class).outType(Person.class).route().process(processor).endRest();
    }
}
