package software_libre.api_luna.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software_libre.api_luna.share.entity.Denuncia;

@Repository
public interface RepositoryDenuncia extends JpaRepository<Denuncia, Long> {
}