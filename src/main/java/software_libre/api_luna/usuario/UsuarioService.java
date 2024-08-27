package software_libre.api_luna.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software_libre.api_luna.share.entity.Usuario;
import software_libre.api_luna.share.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
  @Autowired
  private UserRepository userRepository;

  public Usuario guardarUsuario(Usuario usuario){
      return userRepository.save(usuario);
  }

  public void borrarUsuarioPorUsuario(Usuario usuario){
      userRepository.delete(usuario);
  }

  public void borrarUsuarioPorId(Long id){
      userRepository.deleteById(id);
  }

  public List<Usuario> encontrarTodosLosUsuarios(){
      return userRepository.findAll();
  }

  public Optional<Usuario> encontrarUsuarioPorId(Long id){
      return userRepository.findById(id);
  }

  public Usuario actualizarUsuario(Long id, Usuario usuarioAModificar){
      Optional<Usuario> denunciaOptional = userRepository.findById(id);
      if(denunciaOptional.isPresent()){
          Usuario modificaciones = denunciaOptional.get();

          if (usuarioAModificar.getUsername() != null) {
              modificaciones.setUsername(usuarioAModificar.getUsername());
          }
          if (usuarioAModificar.getEmail() != null) {
              modificaciones.setEmail(usuarioAModificar.getEmail());
          }
          if (usuarioAModificar.getActivo() != null) {
              modificaciones.setActivo(usuarioAModificar.getActivo());
          }
          if (usuarioAModificar.getPassword() != null) {
            modificaciones.setPassword(usuarioAModificar.getPassword());
          }

          return userRepository.save(modificaciones);
      } else {
          return null;
      }
  }
}
