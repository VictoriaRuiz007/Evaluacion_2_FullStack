package Evaluacion.Catalogo.WebClient;

import Evaluacion.Catalogo.model.Videojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UsuarioClient {

    @Autowired
    private WebClient.Builder webClientBuilder;


    public Videojuego obtenerVideojuego(Long idVideojuego) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/catalogo/publico/buscar?id=" + idVideojuego)
                .retrieve()
                .bodyToMono(Videojuego.class)
                .block();
    }
}