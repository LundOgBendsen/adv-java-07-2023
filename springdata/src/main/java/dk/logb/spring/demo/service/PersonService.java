package dk.logb.spring.demo.service;

import dk.logb.spring.demo.model.Address;
import dk.logb.spring.demo.model.Person;
import dk.logb.spring.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository repo;

    //create and save 5 random persons
    public void create5Persons() {
        for (int i = 0; i < 5; i++) {
            Person p1 = newPersonWithAddress();
            repo.save(p1);
        }
    }

    public void createPersons(int number) {
        for (int i = 0; i < number; i++) {
            Person p1 = newPersonWithAddress();
            repo.save(p1);
        }
    }

    //create and return a random person named Sue, John, Luigi, or Jane
    public Person newPersonWithAddress() {
        String[] firstNames = {"Sue", "John", "Luigi", "Jane"};
        String fn = firstNames[(int) (Math.random() * firstNames.length)];

        String[] lastNames = {"Rexton", "Irving", "Barolo", "Hilton"};
        String ln = lastNames[(int) (Math.random() * lastNames.length)];

        Person p1 = new Person(fn, ln, getRandomDate());

        String[] roads = {"Hard drive 1", "The High Road", "By the Way", "High 5 way"};
        String road = roads[(int) (Math.random() * roads.length)];

        //random int from 1000 to 9999
        int zip = (int) (Math.random() * 9000) + 1000;
        Address a1 = new Address(p1, road, "Copenhagen", zip, "Denmark");
        p1.setAddress(a1);
        return p1;
    }

    //get a random localdate between 1900 and 2020
    private LocalDate getRandomDate() {
        int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();
        long randomDay = minDay + (int) (Math.random() * (maxDay - minDay));
        return LocalDate.ofEpochDay(randomDay);
    }




    public List<Person> getAllPersons() {
        return repo.findAll();
    }

    //delete person by id
    public void deletePerson(int id) {
        repo.deleteById(id);
    }

    //find person by id
    public Person getPerson(int id) {
        return repo.findById(id).get();
    }

    //find person by last name
    public List<Person> getPersonByLastName(String lastName) {
        return repo.findByLastName(lastName);
    }

}
