package software_libre.api_luna.denuncia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import software_libre.api_luna.share.entity.Denuncia;
import software_libre.api_luna.share.repository.DenunciaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository repositoryDenuncia;

    public Denuncia guardarDenuncia(Denuncia denuncia) {
        try {
            return repositoryDenuncia.save(denuncia);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando denuncia", e);
        }
    }

    public void borrarDenunciaPorDenuncia(Denuncia denuncia) {
        try {
            repositoryDenuncia.delete(denuncia);
        } catch (Exception e) {
            throw new RuntimeException("Error borrando denuncia", e);
        }
    }

    public void borrarDenunciaPorId(Long id) {
        try {
            repositoryDenuncia.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Denuncia no encontrada para ID: " + id, e);
        } catch (Exception e) {
            throw new RuntimeException("Error borrando denuncia por ID", e);
        }
    }

    public List<Denuncia> encontrarTodasLasDenuncias() {
        return repositoryDenuncia.findAll();
    }

    public Optional<Denuncia> encontrarDenunciaPorId(Long id) {
        return repositoryDenuncia.findById(id);
    }

    public Denuncia actualizarDenuncia(Long id, Denuncia denunciaAModificar) {
        Optional<Denuncia> denunciaOptional = repositoryDenuncia.findById(id);
        if (denunciaOptional.isPresent()) {
            Denuncia modificaciones = denunciaOptional.get();

            if (denunciaAModificar.getResumen() != null) {
                modificaciones.setResumen(denunciaAModificar.getResumen());
            }
            if (denunciaAModificar.getValidado() != null) {
                modificaciones.setValidado(denunciaAModificar.getValidado());
            }
            if (denunciaAModificar.getActivo() != null) {
                modificaciones.setActivo(denunciaAModificar.getActivo());
            }
            if (denunciaAModificar.getCurso() != null){
                modificaciones.setCurso(denunciaAModificar.getCurso());
            }
            if (denunciaAModificar.getLetra() != null){
                modificaciones.setLetra(denunciaAModificar.getLetra());
            }
            if (denunciaAModificar.getDenunciantes() != null){
                modificaciones.setDenunciantes(denunciaAModificar.getDenunciantes());
            }

            return repositoryDenuncia.save(modificaciones);
        } else {
            throw new RuntimeException("Denuncia no encontrada para ID: " + id);
        }
    }
}