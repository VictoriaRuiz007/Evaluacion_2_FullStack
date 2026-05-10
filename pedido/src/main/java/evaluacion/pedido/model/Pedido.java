package evaluacion.pedido.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.Date;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fechaPedido;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Long idCliente;

    @Column(nullable = true)
    private Long idPromocion;






}
