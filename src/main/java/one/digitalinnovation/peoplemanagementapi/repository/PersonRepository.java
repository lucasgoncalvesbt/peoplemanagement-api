package one.digitalinnovation.peoplemanagementapi.repository;

import one.digitalinnovation.peoplemanagementapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
