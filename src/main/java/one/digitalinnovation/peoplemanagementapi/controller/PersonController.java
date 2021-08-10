package one.digitalinnovation.peoplemanagementapi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.peoplemanagementapi.dto.MessageResponseDTO;
import one.digitalinnovation.peoplemanagementapi.dto.PersonDTO;
import one.digitalinnovation.peoplemanagementapi.exception.PersonNotFoundException;
import one.digitalinnovation.peoplemanagementapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> listAll() {
        return ResponseEntity.ok(personService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) throws PersonNotFoundException {
        return ResponseEntity.ok(personService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id,@RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
