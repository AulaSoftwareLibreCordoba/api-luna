package software_libre.api_luna.denuncia;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software_libre.api_luna.share.entity.Denuncia;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaDto {
  private Long id;
  private String resumen;
  private LocalDateTime fechaCreacion;
  private String validado;
  private String activo;

  // Un constructor que no necesita de id
  public DenunciaDto(
          String resumen,
          LocalDateTime fechaCreacion,
          String validado,
          String activo
  ){
    setResumen(resumen);
    setFechaCreacion(fechaCreacion);
    setValidado(validado);
    setActivo(activo);
  }

  // Este método devuelve en caso de que exista una instancia de denunciaDto
  public static DenunciaDto toDto(Denuncia denuncia){
    if(denuncia == null){
      return null;
    }
    return new DenunciaDto(
            denuncia.getId(),
            denuncia.getResumen(),
            denuncia.getFechaCreacion(),
            denuncia.getValidado(),
            denuncia.getActivo()
    );
  }

  public static Denuncia toEntity(DenunciaDto denunciaDto){
    if(denunciaDto == null){
      return null;
    }
    Denuncia.DenunciaBuilder entidad = Denuncia
            .builder()
            .id(denunciaDto.getId())
            .resumen(denunciaDto.getResumen())
            .fechaCreacion(denunciaDto.getFechaCreacion())
            .validado(denunciaDto.getValidado())
            .activo(denunciaDto.getActivo());

    if(denunciaDto.getId() != null){
      entidad.id(denunciaDto.getId());
    }
    return entidad.build();
  }

}
