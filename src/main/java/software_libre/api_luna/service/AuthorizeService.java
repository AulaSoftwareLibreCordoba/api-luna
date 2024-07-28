package software_libre.api_luna.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software_libre.api_luna.loginData.LoginRequest;
import software_libre.api_luna.loginData.RegisterRequest;
import software_libre.api_luna.entity.Usuario;
import software_libre.api_luna.repository.IUserRepository;
import software_libre.api_luna.security.JwtService;

import java.util.List;


@Service
public class AuthorizeService {


    private final IUserRepository userRepository;
    private final JwtService jwtService;

    public AuthorizeService(IUserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest registerRequest){
        Usuario u = userRepository.findByEmail(registerRequest.getEmail());
        if(u!=null)
            throw new EntityExistsException("El usuario ya existe");

        //TODO: Añadir clase de validación de que no vengan los campos nulos, de que no este duplicado el email etc
        Usuario user = Usuario.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .activo("Y")//TODO: remplazar por constante
                .password(registerRequest.getPassword())
                .build();

        userRepository.save(user);

        return "Registrado satisfactoriamente";
    }

    public String authenticate(LoginRequest loginRequest) {
        Usuario user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null) {

            return jwtService.generateToken(user);
        }
        throw new EntityNotFoundException("Usuario no valido");//Personalizar excepcion
    }

}
