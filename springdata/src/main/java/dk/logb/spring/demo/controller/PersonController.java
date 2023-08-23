package dk.logb.spring.demo.controller;

import dk.logb.spring.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dk.logb.spring.demo.service.PersonService;

import java.util.List;

//rest controller for person
@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    PersonService service;


    //get request: get all persons as json
    @GetMapping
    List<Person> getAllPersons() {
        return service.getAllPersons();
    }

    @GetMapping(value = "/create5", produces = "text/plain" )
    String create5() {
        service.createPersons();
        return "5 persons created";
    }


}
