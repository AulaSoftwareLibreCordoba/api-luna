package software_libre.api_luna.login;

import org.springframework.stereotype.Service;
import software_libre.api_luna.share.entity.Rol;
import software_libre.api_luna.exceptions.CustomGenericException;
import software_libre.api_luna.login.loginData.LoginRequest;
import software_libre.api_luna.login.loginData.RegisterRequest;
import software_libre.api_luna.share.entity.Usuario;
import software_libre.api_luna.share.repository.IUserRepository;
import software_libre.api_luna.security.JwtService;

import java.util.ArrayList;
import java.util.List;


@Service
public class LoginService {


    private final IUserRepository userRepository;
    private final JwtService jwtService;

    public LoginService(IUserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest registerRequest){
        Usuario u = userRepository.findByEmail(registerRequest.getEmail());
        if(u!=null)
            throw new CustomGenericException(1001, "usuario ya existe");

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
        Usuario fakeUser = Usuario
                .builder()
                .username("fakeUser8")
                .email("fakeUser8@email.com")
                .activo("Y")
                .build();

        List<Rol> fakeRoles = new ArrayList<>();

        Rol fakeRol1 = Rol
                .builder()
                .nombre("alumno")
                .activo("Y")
                .build();

        Rol fakeRol2 = Rol
                .builder()
                .nombre("profesor")
                .activo("Y")
                .build();

        fakeRoles.add(fakeRol1);
        fakeRoles.add(fakeRol2);

        fakeUser.setRoles(fakeRoles);

        if (user != null) {

            return jwtService.generateToken(fakeUser);
        }
        throw new CustomGenericException(1000, "Usuario no concentrator");
    }

}
