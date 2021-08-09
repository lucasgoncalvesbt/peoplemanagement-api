package one.digitalinnovation.peoplemanagementapi.mapper;

import one.digitalinnovation.peoplemanagementapi.dto.PersonDTO;
import one.digitalinnovation.peoplemanagementapi.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDto(Person person);

}
