package software_libre.api_luna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software_libre.api_luna.entity.Rol;

public interface IRolRepository extends JpaRepository<Rol, Long > {

    Rol findByNombre(String nombre);
}
