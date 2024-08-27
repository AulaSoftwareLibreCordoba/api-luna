package software_libre.api_luna.usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software_libre.api_luna.share.entity.Usuario;


@RestController
@RequestMapping("/users")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @PostMapping
  public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario Usuario){
      Usuario savedUsuario = usuarioService.guardarUsuario(Usuario);
      return ResponseEntity.ok(savedUsuario);
  }

  @DeleteMapping
  public ResponseEntity<Void> borrarUsuarioPorUsuario(@RequestBody Usuario Usuario){
      usuarioService.borrarUsuarioPorUsuario(Usuario);
      return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> borrarUsuarioPorId(@PathVariable Long id){
      if(encontrarUsuario(id)){
          usuarioService.borrarUsuarioPorId(id);
          return ResponseEntity.noContent().build();
      } else {
          return ResponseEntity.notFound().build();
      }
  }

  @GetMapping
  public List<Usuario> encontrarTodasLasUsuarios(){
      return usuarioService.encontrarTodosLosUsuarios();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> encontrarUsuarioPorId(@PathVariable Long id){
      if(encontrarUsuario(id)){
          Optional<Usuario> Usuario = usuarioService.encontrarUsuarioPorId(id);
          return ResponseEntity.ok(Usuario.get());
      } else {
          return ResponseEntity.notFound().build();
      }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Usuario> modificarUsuario(@PathVariable Long id, @RequestBody Usuario Usuario){
      if(encontrarUsuario(id)){
          Usuario UsuarioModificada = usuarioService.actualizarUsuario(id, Usuario);
          return ResponseEntity.ok(UsuarioModificada);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

  private boolean encontrarUsuario(Long id){
      Optional<Usuario> Usuario = usuarioService.encontrarUsuarioPorId(id);
      return Usuario.isPresent();
  }
}
