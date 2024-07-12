package software_libre.api_luna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software_libre.api_luna.dto.UserDto;
import software_libre.api_luna.entity.Usuario;
import software_libre.api_luna.repository.IUserRepository;


@Service
public class AuthorizeService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    private final String secret = "secretoequiposoftwarelibreperonoEsSuficientementeLargo";

    public String authenticate(UserDto userDto) {
        Usuario user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            return jwtService.generateToken(userDto);
        }
        throw new RuntimeException("Usuario no valido");//Personalizar excepcion
    }


}
