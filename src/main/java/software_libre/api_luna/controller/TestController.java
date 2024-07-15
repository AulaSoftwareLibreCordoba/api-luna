package software_libre.api_luna.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.parser.Entity;

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
}
