package software_libre.api_luna.dto;

import lombok.*;
import software_libre.api_luna.entity.Rol;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;

    private List<String> roles;

}
