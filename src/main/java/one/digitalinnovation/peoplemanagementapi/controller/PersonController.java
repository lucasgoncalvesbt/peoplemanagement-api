package one.digitalinnovation.peoplemanagementapi.controller;

import one.digitalinnovation.peoplemanagementapi.dto.MessageResponseDTO;
import one.digitalinnovation.peoplemanagementapi.dto.PersonDTO;
import one.digitalinnovation.peoplemanagementapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }
}
