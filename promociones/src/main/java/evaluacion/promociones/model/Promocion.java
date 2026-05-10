package evaluacion.promociones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "promociones")
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    @Column(unique = true, nullable = false)
    private String codigo;

    @NotNull(message = "El descuento es obligatorio")
    @DecimalMin(value = "0.0", message = "El descuento no puede ser menor a 0")
    @DecimalMax(value = "1.0", message = "El descuento no puede ser mayor a 1.0")
    private Double descuento;
}