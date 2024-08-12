package software_libre.api_luna.pizarra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software_libre.api_luna.share.entity.Pizarra;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizarraDto {

    private Long id;
    private String nombre;
    private String encabezado;
    private String texto;
    private String fechaValidez;
    private String activo;

    public static PizarraDto toDto(Pizarra noticia) {
        if (noticia == null) {
            return null;
        }
        return new PizarraDto(
                noticia.getId(),
                noticia.getNombre(),
                noticia.getEncabezado(),
                noticia.getTexto(),
                noticia.getFechaValidez(),
                noticia.getActivo()
        );
    }

    public static Pizarra toEntity(PizarraDto pizarraDto) {
        if (pizarraDto == null) {
            return null;
        }

        return Pizarra
                .builder()
                .id(pizarraDto.getId())
                .nombre(pizarraDto.getNombre())
                .encabezado(pizarraDto.getEncabezado())
                .texto(pizarraDto.getTexto())
                .activo(pizarraDto.getActivo())
        .build();
    }
}
