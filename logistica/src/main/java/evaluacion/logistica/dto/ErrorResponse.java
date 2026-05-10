package evaluacion.logistica.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime fecha;
    private int estado;
    private String error;
    private String mensaje;
    private String ruta;
    private List<String> errores;
}
