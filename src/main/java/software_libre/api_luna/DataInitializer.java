package software_libre.api_luna;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import software_libre.api_luna.share.entity.Rol;
import software_libre.api_luna.share.entity.Usuario;
import software_libre.api_luna.share.repository.IRolRepository;
import software_libre.api_luna.share.repository.IUserRepository;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {
  private final IRolRepository rolRepository;
  private final IUserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public DataInitializer(IRolRepository rolRepository, IUserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.rolRepository = rolRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Bean
  public CommandLineRunner init() {
    return args -> {
      // Crear roles iniciales si no existen
      createRoleIfNotFound("USER");
      createRoleIfNotFound("PROFESOR");
      createRoleIfNotFound("ADMIN");
      // Puedes agregar más roles si es necesario

      createInitUserIfNotFound("root");
    };
  }

  private void createInitUserIfNotFound(String username) {
    if (userRepository.findByUsername(username) == null) {
      // Obtener roles para el usuario y asegurarse de que están gestionados
      Rol adminRole = rolRepository.findByNombre("ADMIN");
      Rol profesorRole = rolRepository.findByNombre("PROFESOR");
      Rol userRole = rolRepository.findByNombre("USER");

      // Si alguno de los roles es null, significa que aún no existen en la base de datos.
      if (adminRole != null) {
        adminRole = rolRepository.saveAndFlush(adminRole);
      }
      if (profesorRole != null) {
        profesorRole = rolRepository.saveAndFlush(profesorRole);
      }
      if (userRole != null) {
        userRole = rolRepository.saveAndFlush(userRole);
      }

      List<Rol> roles = new ArrayList<>();
      if (adminRole != null) roles.add(adminRole);
      if (userRole != null) roles.add(userRole);
      if (profesorRole != null) roles.add(profesorRole);

      // Crear el usuario con los roles ya gestionados
      Usuario user = Usuario.builder()
              .id(1L)
              .username(username)
              .email("root@root.root")
              .password(passwordEncoder.encode("root"))
              .activo("yes")
              .roles(roles)
              .build();

      // Guardar el usuario en la base de datos
      userRepository.save(user);
    }
  }


  private void createRoleIfNotFound(String roleName) {
    if (rolRepository.findByNombre(roleName) == null) {
      Rol role = new Rol();
      role.setNombre(roleName);
      role.setActivo("Si");
      rolRepository.save(role);
    }
  }
}
