package software_libre.api_luna.pizarra;

import org.springframework.data.jpa.repository.JpaRepository;
import software_libre.api_luna.share.entity.Pizarra;

public interface IPizarraRepository extends JpaRepository<Pizarra, Long> {
}
