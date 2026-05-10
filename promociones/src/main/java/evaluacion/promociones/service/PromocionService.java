package evaluacion.promociones.service;

import evaluacion.promociones.model.Promocion;
import evaluacion.promociones.repository.PromocionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromocionService {

    private final PromocionRepository promocionRepository;

    public List<Promocion> listarPromociones() {
        log.info("Obteniendo lista de todas las promociones");
        return promocionRepository.findAll();
    }

    public Promocion buscarPorCodigo(String codigo) {
        log.info("Buscando promocion con codigo: {}", codigo);
        return promocionRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Promoción no encontrada con el código: " + codigo));
    }

    public Promocion crearPromocion(Promocion promocion) {
        log.info("Creando nueva promocion con codigo: {}", promocion.getCodigo());
        return promocionRepository.save(promocion);
    }

    public void eliminarPromocion(Long id) {
        log.info("Eliminando promocion con id: {}", id);
        if (!promocionRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Promoción no encontrada con id: " + id);
        }
        promocionRepository.deleteById(id);
    }
}