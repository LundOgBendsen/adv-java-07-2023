package dk.logb.spring.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dk.logb.spring.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dk.logb.spring.demo.service.PersonService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        service.create5Persons();
        return "5 persons created";
    }


    @GetMapping(value = "/create", produces = "text/plain" )
    String create5(@RequestParam("amount") int amount){
        service.createPersons(amount);
        return amount + " persons created";
    }

    @GetMapping(value = "/ages", produces = "text/plain" )
    String getAges() {
        Stream<Person> stream = service.getAllPersons().stream();
        Map<String, Integer> map = stream.collect(Collectors.toMap(this::getFullName, this::getAgeFromBirthDate));
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    @GetMapping(value = "/boomers", produces = "text/plain" )
    String getBoomers() {
        Stream<Person> stream = service.getAllPersons().stream();
        Map<String, Integer> map = stream.collect(Collectors.toMap(this::getFullName, this::getAgeFromBirthDate));
        Map<String, Integer> boomers = map.entrySet().stream().filter(e -> e.getValue() >= 65).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(boomers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    @GetMapping(value = "/boomers/female", produces = "text/plain" )
    String getFemaleBoomers() {
        Stream<Person> stream = service.getAllPersons().stream();
        Map<String, Integer> map = stream.filter(p -> "Jane".equals(p.getFirstName()) || "Sue".equals(p.getFirstName()) ).collect(Collectors.toMap(this::getFullName, this::getAgeFromBirthDate));

        Map<String, Integer> boomers = map.entrySet().stream().filter(e -> e.getValue() >= 65).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(boomers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    @GetMapping(value = "/groupbyfirstname", produces = "text/plain" )
    String getQuery() {
        Stream<Person> stream = service.getAllPersons().stream();
        //count persons group by first name
        Map<String, Long> map = stream.collect(Collectors.groupingBy(Person::getFirstName, Collectors.counting()));
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    @GetMapping(value = "/oldest", produces = "text/plain" )
    public String getOldest() {
        Stream<Person> stream = service.getAllPersons().stream();
        //find the oldest person with each first name
        Map<String, Person> map = stream.collect(Collectors.groupingBy(Person::getFirstName,
                Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(Person::getBirthDate)), Optional::get)));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json;
        try {
            json = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    @GetMapping(value = "/q", produces = "text/plain" )
    public String q() {
        Stream<Person> stream = service.getAllPersons().stream();
        List<String> list = stream.map(p -> p.getFirstName() + " " + p.getLastName() + ", " + p.getAddress().getCity() + ", " + p.getBirthDate())
                .filter(s -> s.contains("Sue"))
                .map(this::reverse).collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json;
        try {
            json = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    private String getFullName(Person p) {
        return p.getFirstName() + " " + p.getLastName() + ", " + p.getAddress().getCity() + ", " + p.getBirthDate();
    }

    //private: get age from birthdate
    private int getAgeFromBirthDate(Person p) {
        LocalDate birthDate = p.getBirthDate();
        LocalDate now = LocalDate.now();
        int age = now.getYear() - birthDate.getYear();
        if (now.getMonthValue() < birthDate.getMonthValue()) {
            age--;
        } else if (now.getMonthValue() == birthDate.getMonthValue() && now.getDayOfMonth() < birthDate.getDayOfMonth()) {
            age--;
        }
        return age;
    }


}
