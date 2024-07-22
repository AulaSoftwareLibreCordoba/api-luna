package software_libre.api_luna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import software_libre.api_luna.exceptions.CustomGenericException;
import software_libre.api_luna.exceptions.ExceptionResponse;
import software_libre.api_luna.exceptions.ExceptionResponseBuilder;


@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping()
    public ResponseEntity<?> testGet(){
        return ResponseEntity.status(200).body("Conectado satisfactoriamente");
    }

    @GetMapping("/testAdmin")
    public ResponseEntity<?> testGet1(){
        return ResponseEntity.status(200).body("Conectado con admin");
    }

    @GetMapping("/error")
    public ResponseEntity<?> testError(){
      try{
           testExcepton();
       }catch (Exception ex){
           //return ExceptionResponseBuilder.getErrorResponse(ex);
          return ExceptionResponseBuilder.getErrorResponse(ex);
       }
      return ResponseEntity.status(400).body(new ExceptionResponse(2,"gi"));
    }

    private void testExcepton(){
        throw new CustomGenericException(201,"Testing custom exceptions, FUNCIONA");
    }
}
