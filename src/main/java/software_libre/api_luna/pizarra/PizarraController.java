package software_libre.api_luna.pizarra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software_libre.api_luna.exceptions.ExceptionResponseBuilder;

@RestController
@RequestMapping("/dashboardpizarra")
public class PizarraController {

    @Autowired
    PizarraService service;

    @GetMapping
    public ResponseEntity<?> getAll(@PageableDefault(size = 4) Pageable dataPageable){
        try{
            return ResponseEntity.status(200).body(service.getAll(dataPageable));
        }catch(Exception ex){
            return ExceptionResponseBuilder.getErrorResponse(ex);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOne(@RequestBody PizarraDto pizarraDto)
    {
        return ResponseEntity.status(400).body("no programado");
    }
}
