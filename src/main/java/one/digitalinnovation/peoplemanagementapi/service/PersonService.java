package one.digitalinnovation.peoplemanagementapi.service;

import one.digitalinnovation.peoplemanagementapi.dto.MessageResponseDTO;
import one.digitalinnovation.peoplemanagementapi.model.Person;
import one.digitalinnovation.peoplemanagementapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with id: " + savedPerson.getId())
                .build();
    }
}
