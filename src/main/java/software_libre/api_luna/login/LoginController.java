package software_libre.api_luna.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import software_libre.api_luna.login.loginData.LoginRequest;
import software_libre.api_luna.login.loginData.RegisterRequest;

@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private LoginService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        String result = service.register(registerRequest);
        return ResponseEntity.status(200).body(result);//TODO: Esto le falta validaciones caso de error etc
    }

    @PostMapping()
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest){
        try {
            String token = service.authenticate(loginRequest);
            return ResponseEntity.status(200).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }



}
