package software_libre.api_luna.noticia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software_libre.api_luna.share.entity.Noticia;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticiaDto {

    private Long id;
    private String nombre;
    private String encabezado;
    private String texto;
    private LocalDateTime fechaValidez;
    private String activo;

    //Constructor sin id
    public NoticiaDto(
            String nombre,
            String encabezado,
            String texto,
            LocalDateTime fechaValidez,
            String activo
    ){
        this.nombre = nombre;
        this.encabezado = encabezado;
        this.texto = texto;
        this.fechaValidez = fechaValidez;
        this.activo = activo;
    }

    public static NoticiaDto toDto(Noticia noticia) {
        if (noticia == null) {
            return null;
        }
        return new NoticiaDto(
                noticia.getId(),
                noticia.getNombre(),
                noticia.getEncabezado(),
                noticia.getTexto(),
                noticia.getFechaValidez(),
                noticia.getActivo()
        );
    }

    public static Noticia toEntity(NoticiaDto noticiaDto) {
        if (noticiaDto == null) {
            return null;
        }

             Noticia.NoticiaBuilder entidad = Noticia
                .builder()
                .id(noticiaDto.getId())
                .nombre(noticiaDto.getNombre())
                .encabezado(noticiaDto.getEncabezado())
                .texto(noticiaDto.getTexto())
                .fechaValidez(noticiaDto.getFechaValidez())
                .activo(noticiaDto.getActivo());

            if(noticiaDto.getId() != null)
                entidad.id(noticiaDto.getId());

        return entidad.build();
    }
}
