package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MenssageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotfindException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MenssageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Pessoa Criada com o Id: ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotfindException {
        Person person = verifyIfExist(id);

        return personMapper.toDTO(person );
    }

    public void delete(Long id) throws PersonNotfindException {
        verifyIfExist(id);
        
        personRepository.deleteById(id);
    }

    public MenssageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotfindException {
        verifyIfExist(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Atualização realizada da pessoa com o Id: ");
    }

    private Person verifyIfExist(Long id) throws PersonNotfindException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotfindException(id));
    }

    private MenssageResponseDTO createMessageResponse(Long id, String message) {
        return MenssageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
