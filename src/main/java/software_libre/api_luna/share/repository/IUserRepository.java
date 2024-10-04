package software_libre.api_luna.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software_libre.api_luna.share.entity.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;

public interface IUserRepository extends JpaRepository<Usuario, Long> {

    @EntityGraph(attributePaths = {"roles"})//Con esta query forzamos un eagger loading
    Usuario findByEmail(String email);

    Usuario findByUsername(String username);
}
