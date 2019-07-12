package com.javatechie.camelrestdsl;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    List<Person> persons = new ArrayList<>();

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 5; i++) {
            persons.add(new Person(i, "person" + i));
        }
    }

    public Person addPerson(Person person) {
        persons.add(person);
        return person;
    }

    public List<Person> getPersons() {
        return persons;

    }
}
