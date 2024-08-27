package software_libre.api_luna.denuncia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software_libre.api_luna.share.entity.Denuncia;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService serviceDenuncia;

    private static final Logger logger = LoggerFactory.getLogger(DenunciaService.class);

    @PostMapping
    public ResponseEntity<Denuncia> guardarDenuncia(@RequestBody Denuncia denuncia){
        Denuncia savedDenuncia = serviceDenuncia.guardarDenuncia(denuncia);
        return ResponseEntity.ok(savedDenuncia);
    }

    @DeleteMapping
    public ResponseEntity<Void> borrarDenunciaPorDenuncia(@RequestBody Denuncia denuncia){
        serviceDenuncia.borrarDenunciaPorDenuncia(denuncia);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarDenunciaPorId(@PathVariable Long id){
        if(encontrarDenuncia(id)){
            serviceDenuncia.borrarDenunciaPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Denuncia> encontrarTodasLasDenuncias(){
        logger.info("Se han buscado todas las denuncias");
        return serviceDenuncia.encontrarTodasLasDenuncias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> encontrarDenunciaPorId(@PathVariable Long id){
        if(encontrarDenuncia(id)){
            Optional<Denuncia> denuncia = serviceDenuncia.encontrarDenunciaPorId(id);
            return ResponseEntity.ok(denuncia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> modificarDenuncia(@PathVariable Long id, @RequestBody Denuncia denuncia){
        if(encontrarDenuncia(id)){
            Denuncia denunciaModificada = serviceDenuncia.actualizarDenuncia(id, denuncia);
            return ResponseEntity.ok(denunciaModificada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private boolean encontrarDenuncia(Long id){
        Optional<Denuncia> denuncia = serviceDenuncia.encontrarDenunciaPorId(id);
        return denuncia.isPresent();
    }
}
