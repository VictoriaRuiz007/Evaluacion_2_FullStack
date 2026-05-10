package evaluacion.logistica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "seguimientos")
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String codigoSeguimiento;

    @Column(nullable = false, length = 100)
    private String empresaEnvio;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(nullable = false)
    private Date fechaEstimada;

    @Column(nullable = false)
    private Long idPedido;
}
