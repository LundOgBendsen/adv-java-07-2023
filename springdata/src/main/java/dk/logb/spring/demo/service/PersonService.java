package dk.logb.spring.demo.service;

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
    public void createPersons() {
        repo.save(new Person("John", "Doe", LocalDate.now()));
        repo.save(new Person("Jenny", "Doe", LocalDate.now()));
        repo.save(new Person("Jane", "Foe", LocalDate.now()));
        repo.save(new Person("Fanny", "Moe", LocalDate.now()));
        repo.save(new Person("Bo", "Toe", LocalDate.now()));
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
