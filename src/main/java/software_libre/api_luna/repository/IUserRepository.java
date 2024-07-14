package software_libre.api_luna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software_libre.api_luna.entity.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Usuario, Long> {

    @EntityGraph(attributePaths = {"roles"})//Con esta query forzamos un eagger loading
    Usuario findByEmail(String email);
}
