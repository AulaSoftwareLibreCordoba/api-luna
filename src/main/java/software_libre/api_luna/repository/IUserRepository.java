package software_libre.api_luna.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import software_libre.api_luna.entity.Usuario;

public interface IUserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
