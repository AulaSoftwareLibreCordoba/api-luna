package software_libre.api_luna.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software_libre.api_luna.share.entity.Usuario;

@Repository
public interface UserRepository  extends JpaRepository<Usuario, Long>{
}
