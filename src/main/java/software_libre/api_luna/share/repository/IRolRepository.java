package software_libre.api_luna.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software_libre.api_luna.share.entity.Rol;

public interface IRolRepository extends JpaRepository<Rol, Long > {

    Rol findByNombre(String nombre);
}
