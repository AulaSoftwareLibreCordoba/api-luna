package software_libre.api_luna.share.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import software_libre.api_luna.share.enums.Curso;
import software_libre.api_luna.share.enums.Letra;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String denunciantes;

    private Curso curso;

    private Letra letra;

    private String resumen;

    private LocalDateTime fechaCreacion;

    private String validado;

    private String activo;

    @OneToMany(mappedBy = "denuncia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventoDenuncia> eventoDenuncias;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

}