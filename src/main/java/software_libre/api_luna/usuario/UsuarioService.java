package software_libre.api_luna.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software_libre.api_luna.share.entity.Usuario;
import software_libre.api_luna.share.entity.Rol;
import software_libre.api_luna.share.repository.IRolRepository;
import software_libre.api_luna.share.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private IRolRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public Usuario guardarUsuario(Usuario usuario) {
    // Asegúrate de que los roles están en el estado gestionado
    List<Rol> roles = usuario.getRoles().stream()
            .map(rol -> roleRepository.findById(rol.getId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rol.getId())))
            .collect(Collectors.toList());

    usuario.setRoles(roles);
    String encodePassword = passwordEncoder.encode(usuario.getPassword());
    usuario.setPassword(encodePassword);
    return userRepository.save(usuario);
  }

  public void borrarUsuarioPorUsuario(Usuario usuario) {
    userRepository.delete(usuario);
  }

  public void borrarUsuarioPorId(Long id) {
    userRepository.deleteById(id);
  }

  public List<Usuario> encontrarTodosLosUsuarios() {
    return userRepository.findAll();
  }

  public Optional<Usuario> encontrarUsuarioPorId(Long id) {
    return userRepository.findById(id);
  }

  public Usuario actualizarUsuario(Long id, Usuario usuarioAModificar) {
    Optional<Usuario> usuarioOptional = userRepository.findById(id);
    if (usuarioOptional.isPresent()) {
      Usuario usuarioExistente = usuarioOptional.get();

      if (usuarioAModificar.getUsername() != null) {
        usuarioExistente.setUsername(usuarioAModificar.getUsername());
      }
      if (usuarioAModificar.getEmail() != null) {
        usuarioExistente.setEmail(usuarioAModificar.getEmail());
      }
      if (usuarioAModificar.getActivo() != null) {
        usuarioExistente.setActivo(usuarioAModificar.getActivo());
      }
      if (usuarioAModificar.getPassword() != null) {
        usuarioExistente.setPassword(usuarioAModificar.getPassword());
      }

      // Actualiza roles si se proporcionan
      if (usuarioAModificar.getRoles() != null && !usuarioAModificar.getRoles().isEmpty()) {
        List<Rol> roles = usuarioAModificar.getRoles().stream()
                .map(rol -> roleRepository.findById(rol.getId())
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rol.getId())))
                .collect(Collectors.toList());
        usuarioExistente.setRoles(roles);
      }

      return userRepository.save(usuarioExistente);
    } else {
      return null;
    }
  }
}
