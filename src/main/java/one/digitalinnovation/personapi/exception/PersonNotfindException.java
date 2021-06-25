package one.digitalinnovation.personapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotfindException extends Exception {
    public PersonNotfindException(Long id) {
        super("Nenhuma pessoa foi encontrada com o Id: " + id);
    }
}
