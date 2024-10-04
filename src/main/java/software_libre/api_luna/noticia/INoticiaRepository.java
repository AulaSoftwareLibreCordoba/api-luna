package software_libre.api_luna.noticia;

import org.springframework.data.jpa.repository.JpaRepository;
import software_libre.api_luna.share.entity.Noticia;

public interface INoticiaRepository extends JpaRepository<Noticia, Long> {
}
