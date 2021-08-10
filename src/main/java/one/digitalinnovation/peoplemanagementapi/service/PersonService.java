package one.digitalinnovation.peoplemanagementapi.service;

import one.digitalinnovation.peoplemanagementapi.dto.MessageResponseDTO;
import one.digitalinnovation.peoplemanagementapi.dto.PersonDTO;
import one.digitalinnovation.peoplemanagementapi.mapper.PersonMapper;
import one.digitalinnovation.peoplemanagementapi.model.Person;
import one.digitalinnovation.peoplemanagementapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper  personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message("Created person with id: " + savedPerson.getId())
                .build();
    }
}
