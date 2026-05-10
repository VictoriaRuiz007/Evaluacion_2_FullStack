package evaluacion.pedido.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class UsuarioClient {

    private final WebClient webClient;

    @Autowired
    public UsuarioClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/api/usuarios").build();
    }

    public boolean verificarUsuarioExiste(Long idCliente) {
        log.info("Verificando existencia del usuario con ID: {} en el microservicio USUARIO", idCliente);

        try {
            this.webClient.get()
                    .uri("/{id}", idCliente) // Ajusta esta ruta a tu Controller de Usuario
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block(); // .block() hace que la llamada sea síncrona
            return true;
        } catch (Exception e) {
            log.error("Error al buscar el usuario (puede que no exista): {}", e.getMessage());
            return false;
        }
    }
}
