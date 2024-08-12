package software_libre.api_luna.pizarra;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import software_libre.api_luna.share.entity.Pizarra;
import software_libre.api_luna.exceptions.CustomGenericException;


@Service
public class PizarraService {

    @Autowired
    private IPizarraRepository pizarraRepository;

    public Page<PizarraDto> getAll(Pageable dataPageable){

        if(dataPageable.getPageNumber() < 0 || dataPageable.getPageSize() < 1)//Si en la request viene mal configurado el pageable
            throw new CustomGenericException(2000, "Datos pageables no válidos");

        Page<Pizarra> entities = pizarraRepository.findAll(dataPageable);//Metodo heredado de JPA

        if(entities.isEmpty())//Si no hay datos en la base de datos
            throw new CustomGenericException(2000, "No se han encontrados datos");

        return entities.map(e -> PizarraDto.toDto(e));//Transformamos las entidades en dto

    }
}
