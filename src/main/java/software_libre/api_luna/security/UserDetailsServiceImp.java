package software_libre.api_luna.security;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import software_libre.api_luna.share.entity.Rol;
import software_libre.api_luna.share.entity.Usuario;
import software_libre.api_luna.share.repository.IRolRepository;
import software_libre.api_luna.share.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        UserDetails user = userRepository.findByEmail(email);

        if(user == null){
            Usuario newUser = Usuario
                    .builder()
                    .email(email)
                    .activo("Y")
                    .username(email)
                    .password("default")
                    .build();

            //Entidad rol "alumno" es la que se usara por defecto
            Rol defaultRol = rolRepository.findByNombre("alumno");//TODO: constante del nombre de la entidad default
            List<Rol> rolList = new ArrayList<>();
            rolList.add(defaultRol);

            newUser.setRoles(rolList);

            userRepository.save(newUser);

            user = userRepository.findByEmail(email);
            //throw new CustomGenericException(4001,"Usuario no autenticado");

        }
        return user;
    }
}
