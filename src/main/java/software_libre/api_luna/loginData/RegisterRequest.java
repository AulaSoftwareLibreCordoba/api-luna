package software_libre.api_luna.loginData;

import lombok.*;
import software_libre.api_luna.entity.Rol;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private String activo;
}
