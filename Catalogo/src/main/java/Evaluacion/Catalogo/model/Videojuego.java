package Evaluacion.Catalogo.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="videojuegos")

public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String plataforma;
    private Integer stock;
    private String categoria;
    private Double precio;
}
