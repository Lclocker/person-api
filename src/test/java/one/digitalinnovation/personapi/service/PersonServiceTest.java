package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.Utils.PersonUtils;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MenssageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.personapi.Utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void TestGiverPersonDTOThenReturnSaveMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expSavedPerson);

        MenssageResponseDTO expSucessMessage = createExpMessageResponse(expSavedPerson.getId());

        MenssageResponseDTO sucessMessage = personService.createPerson(personDTO);

        assertEquals(expSucessMessage, sucessMessage);
    }

    private MenssageResponseDTO createExpMessageResponse(Long id) {
        return MenssageResponseDTO
                .builder()
                .message("Pessoa Criada com o Id: " + id)
                .build();
    }
}
