package dk.logb.spring.demo.repository;

//spring data jpa repository

import dk.logb.spring.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByLastName(String lastName);
}
