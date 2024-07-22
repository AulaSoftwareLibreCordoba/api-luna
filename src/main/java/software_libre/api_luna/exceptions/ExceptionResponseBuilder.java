package software_libre.api_luna.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResponseBuilder {


    //TODO: Plantear la opcion de añadir un status code para dar más info
    public static ResponseEntity<ExceptionResponse> getErrorResponse(Exception ex){
        if(ex instanceof CustomGenericException castedException) {
            return ResponseEntity.status(400).body(new ExceptionResponse(castedException.getCode(), castedException.getDescription()));
        }
        return ResponseEntity.status(400).body(new ExceptionResponse(-1, ex.getMessage()));
    }

}
