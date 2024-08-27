package software_libre.api_luna.usuario;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software_libre.api_luna.share.entity.Usuario;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
  private Long id;
  private String username;
  private String email;
  private String password;
  private String activo;

  public UsuarioDto(String username, String email, String password, String activo){
    setUsername(username);
    setEmail(email);
    setPassword(password);
    setActivo(activo);
  }

  public static Usuario toEntity(UsuarioDto usuarioDto){
    if(usuarioDto == null){
      return null;
    }
    Usuario.UsuarioBuilder entidad = Usuario
        .builder()
        .id(usuarioDto.getId())
        .username(usuarioDto.getUsername())
        .email(usuarioDto.getEmail())
        .password(usuarioDto.getPassword())
        .activo(usuarioDto.getActivo());

    if(usuarioDto.getId() != null){
      entidad.id(usuarioDto.getId());
    }
    return entidad.build();        
  }
}
