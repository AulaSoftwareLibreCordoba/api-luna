package software_libre.api_luna.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import software_libre.api_luna.repository.IUserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails user = repository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Usuario no valido");
        }
        return user;
    }
}
