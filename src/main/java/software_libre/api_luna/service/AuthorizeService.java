package software_libre.api_luna.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import software_libre.api_luna.dto.UserDto;
import software_libre.api_luna.entity.Usuario;
import software_libre.api_luna.repository.IUserRepository;

import java.util.Date;

@Service
public class AuthorizeService {

    @Autowired
    private IUserRepository userRepository;

    private final String secret = "secretoaqui";

    public String authenticate(UserDto userDto) {
        Usuario user = userRepository.findByEmail(userDto.getEmail());
        if (user != null && new BCryptPasswordEncoder().matches(userDto.getPassword(), user.getPassword())) {
            return generateToken(user);
        }
        throw new RuntimeException("Usuario no valido");//Personalizar excepcion
    }
    //Este metodo esta depcrecated, hay que buscar una alternativa mas acutal
    private String generateToken(Usuario user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60000)) // 1hora
                .signWith(SignatureAlgorithm.HS512, "Secreto")
                .compact();
    }

}
