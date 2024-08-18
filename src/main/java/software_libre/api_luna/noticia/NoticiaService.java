package software_libre.api_luna.noticia;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software_libre.api_luna.share.entity.Noticia;
import software_libre.api_luna.exceptions.CustomGenericException;

import java.util.Objects;


@Service
public class NoticiaService {

    @Autowired
    private INoticiaRepository noticiaRepository;

    public Page<NoticiaDto> getAll(Pageable dataPageable){

        if(dataPageable.getPageNumber() < 0 || dataPageable.getPageSize() < 1)//Si en la request viene mal configurado el pageable
            throw new CustomGenericException(2000, "Datos pageables no válidos");

        Page<Noticia> entities = noticiaRepository.findAll(dataPageable);//Metodo heredado de JPA

        if(entities.isEmpty())//Si no hay datos en la base de datos
            throw new CustomGenericException(2000, "No se han encontrados datos");

        return entities.map(e -> NoticiaDto.toDto(e));//Transformamos las entidades en dto

    }

    public ResponseEntity<Void> createOne(NoticiaDto dto){

        if(dto== null)
            throw new CustomGenericException(2000, "Datos no válidos");

        noticiaRepository.save(NoticiaDto.toEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<NoticiaDto> updateOne(NoticiaDto dto, Long id){
        if(!Objects.equals(dto.getId(), id))
            throw new CustomGenericException(2000, "El objeto seleccionado no conincide");

        if(!noticiaRepository.existsById(id))
            throw new CustomGenericException(2000, "El objeto que se intenta actualizar no existe");

        noticiaRepository.save(NoticiaDto.toEntity(dto));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    public ResponseEntity<Void> deleteOne(Long id){
        if(noticiaRepository.existsById(id))
            noticiaRepository.deleteById(id);
        else
            throw new CustomGenericException(2000, "El objeto que se intenta borrar no existe");

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
