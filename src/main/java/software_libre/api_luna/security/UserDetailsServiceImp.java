package software_libre.api_luna.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import software_libre.api_luna.entity.Rol;
import software_libre.api_luna.entity.Usuario;
import software_libre.api_luna.exceptions.CustomGenericException;
import software_libre.api_luna.repository.IRolRepository;
import software_libre.api_luna.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDetails user = userRepository.findByEmail(email);

        if(user == null){
            Usuario newUser = Usuario
                    .builder()
                    .email(email)
                    .activo("Y")
                    .username(email)
                    .build();

            //Entidad rol "alumno" es la que se usara por defecto
            Rol defaultRol = rolRepository.findByNombre("alumno");//TODO: constante del nombre de la entidad default
            List<Rol> rolList = new ArrayList<>();
            rolList.add(defaultRol);

            newUser.setRoles(rolList);

            userRepository.save(newUser);

            //throw new CustomGenericException(4001,"Usuario no autenticado");
        }
        return user;
    }
}
