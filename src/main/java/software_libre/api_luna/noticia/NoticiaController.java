package software_libre.api_luna.noticia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software_libre.api_luna.exceptions.ExceptionResponseBuilder;

@RestController
@RequestMapping("/dashboardpizarra")
public class NoticiaController {

    @Autowired
    NoticiaService service;

    @GetMapping
    public ResponseEntity<?> getAll(@PageableDefault(size = 4) Pageable dataPageable){
        try{
            return ResponseEntity.status(200).body(service.getAll(dataPageable));
        }catch(Exception ex){
            return ExceptionResponseBuilder.getErrorResponse(ex);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOne(@RequestBody NoticiaDto dto)
    {
        try{
            return service.createOne(dto);
        } catch (Exception ex) {
            return ExceptionResponseBuilder.getErrorResponse(ex);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateOne(@RequestBody NoticiaDto dto, Long id){
        try{
            return service.updateOne(dto, id);
        } catch (Exception ex) {
            return ExceptionResponseBuilder.getErrorResponse(ex);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOne(@RequestBody Long id) {
        try {
            return service.deleteOne(id);
        } catch (Exception ex){
            return ExceptionResponseBuilder.getErrorResponse(ex);
        }
    }
}
