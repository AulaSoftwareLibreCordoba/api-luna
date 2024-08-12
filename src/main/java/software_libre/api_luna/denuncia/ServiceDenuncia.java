package software_libre.api_luna.denuncia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software_libre.api_luna.share.entity.Denuncia;
import software_libre.api_luna.share.repository.RepositoryDenuncia;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceDenuncia {

    @Autowired
    private RepositoryDenuncia repositoryDenuncia;

    public Denuncia guardarDenuncia(Denuncia denuncia){
        return repositoryDenuncia.save(denuncia);
    }

    public void borrarDenunciaPorDenuncia(Denuncia denuncia){
        repositoryDenuncia.delete(denuncia);
    }

    public void borrarDenunciaPorId(Long id){
        repositoryDenuncia.deleteById(id);
    }

    public List<Denuncia> encontrarTodasLasDenuncias(){
        return repositoryDenuncia.findAll();
    }

    public Optional<Denuncia> encontrarDenunciaPorId(Long id){
        return repositoryDenuncia.findById(id);
    }

    public Denuncia actualizarDenuncia(Long id, Denuncia denunciaAModificar){
        Optional<Denuncia> denunciaOptional = repositoryDenuncia.findById(id);
        if(denunciaOptional.isPresent()){
            Denuncia modificaciones = denunciaOptional.get();

            // Actualiza solo los campos que se deben modificar
            if (denunciaAModificar.getResumen() != null) {
                modificaciones.setResumen(denunciaAModificar.getResumen());
            }
            if (denunciaAModificar.getValidado() != null) {
                modificaciones.setValidado(denunciaAModificar.getValidado());
            }
            if (denunciaAModificar.getActivo() != null) {
                modificaciones.setActivo(denunciaAModificar.getActivo());
            }

            return repositoryDenuncia.save(modificaciones);
        } else {
            return null;
        }
    }
}