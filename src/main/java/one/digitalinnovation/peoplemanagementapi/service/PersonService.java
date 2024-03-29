package one.digitalinnovation.peoplemanagementapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.peoplemanagementapi.dto.MessageResponseDTO;
import one.digitalinnovation.peoplemanagementapi.dto.PersonDTO;
import one.digitalinnovation.peoplemanagementapi.exception.PersonNotFoundException;
import one.digitalinnovation.peoplemanagementapi.mapper.PersonMapper;
import one.digitalinnovation.peoplemanagementapi.model.Person;
import one.digitalinnovation.peoplemanagementapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;
    private final PersonMapper  personMapper = PersonMapper.INSTANCE;

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);

        return creatMethodResponse(savedPerson.getId(), "Created person with id: ");
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        //Optional<Person> optionalPerson = personRepository.findById(id);
        //if (optionalPerson.isEmpty()) {
        //    throw new PersonNotFoundException(id);
        //}
        return personMapper.toDto(person);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        this.findById(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToUpdate);

        return creatMethodResponse(savedPerson.getId(), "Updated person with id: ");

    }

    public void deleteById(Long id) throws PersonNotFoundException {
        this.findById(id);
        personRepository.deleteById(id);
    }



    private MessageResponseDTO creatMethodResponse(Long id, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }

}
