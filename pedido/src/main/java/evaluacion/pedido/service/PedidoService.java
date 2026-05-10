package evaluacion.pedido.service;

import evaluacion.pedido.webclient.UsuarioClient;
import evaluacion.pedido.model.Pedido;
import evaluacion.pedido.repository.PedidoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorID(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido crearPedido(Pedido pedido) {
        log.info("Iniciando creación de pedido para el cliente ID: {}", pedido.getIdCliente());


        boolean usuarioExiste = usuarioClient.verificarUsuarioExiste(pedido.getIdCliente());

        if (!usuarioExiste) {
            log.error("No se puede crear el pedido: El cliente con ID {} no existe.", pedido.getIdCliente());
            throw new RuntimeException("El cliente especificado no existe en el sistema.");
        }

        log.info("Cliente verificado. Guardando pedido...");
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}