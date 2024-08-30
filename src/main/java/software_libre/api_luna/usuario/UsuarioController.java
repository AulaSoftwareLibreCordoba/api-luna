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
  public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
    Usuario savedUsuario = usuarioService.guardarUsuario(usuario);
    return ResponseEntity.ok(savedUsuario);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> borrarUsuarioPorId(@PathVariable Long id) {
    if (encontrarUsuario(id)) {
      usuarioService.borrarUsuarioPorId(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public List<Usuario> encontrarTodasLasUsuarios() {
    return usuarioService.encontrarTodosLosUsuarios();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> encontrarUsuarioPorId(@PathVariable Long id) {
    if (encontrarUsuario(id)) {
      Optional<Usuario> usuario = usuarioService.encontrarUsuarioPorId(id);
      return ResponseEntity.ok(usuario.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Usuario> modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
    if (encontrarUsuario(id)) {
      Usuario usuarioModificado = usuarioService.actualizarUsuario(id, usuario);
      return ResponseEntity.ok(usuarioModificado);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  private boolean encontrarUsuario(Long id) {
    Optional<Usuario> usuario = usuarioService.encontrarUsuarioPorId(id);
    return usuario.isPresent();
  }
}

