package software_libre.api_luna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import software_libre.api_luna.dto.UserDto;
import software_libre.api_luna.service.AuthorizeService;

@RestController
@RequestMapping("/authorization")
public class AuthorizeController {


    @Autowired
    private AuthorizeService service;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> login (@RequestBody UserDto user){
        try {
            String token = service.authenticate(user);
            return ResponseEntity.status(200).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
