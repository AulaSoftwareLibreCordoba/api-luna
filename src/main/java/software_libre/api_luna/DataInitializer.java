package software_libre.api_luna;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

  public DataInitializer(IRolRepository rolRepository, IUserRepository userRepository) {
    this.rolRepository = rolRepository;
    this.userRepository = userRepository;
  }

  @Bean
  public CommandLineRunner init() {
    return args -> {
      // Crear roles iniciales si no existen
      createRoleIfNotFound("USER");
      createRoleIfNotFound("ADMIN");
      // Puedes agregar más roles si es necesario

      createInitUserIfNotFound(1L);
    };
  }

  private void createInitUserIfNotFound(Long id) {
    if (!userRepository.findById(id).isPresent()) {
      // Obtener roles para el usuario
      Rol adminRole = rolRepository.findByNombre("ADMIN");
      Rol userRole = rolRepository.findByNombre("USER");

      List<Rol> roles = new ArrayList<>();
      if (adminRole != null) roles.add(adminRole);
      if (userRole != null) roles.add(userRole);

      // Crear el usuario
      Usuario user = Usuario.builder()
              .id(id)
              .username("root")
              .email(null)
              .password("root")
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
