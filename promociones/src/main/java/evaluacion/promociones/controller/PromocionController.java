package evaluacion.promociones.controller;

import evaluacion.promociones.model.Promocion;
import evaluacion.promociones.service.PromocionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promociones")
@RequiredArgsConstructor
public class PromocionController {

    private final PromocionService promocionService;

    @GetMapping("/publico")
    public ResponseEntity<List<Promocion>> listarPromociones() {
        return ResponseEntity.ok(promocionService.listarPromociones());
    }

    @GetMapping("/publico/buscar/{codigo}")
    public ResponseEntity<Promocion> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(promocionService.buscarPorCodigo(codigo));
    }

    @PostMapping("/admin/crear")
    public ResponseEntity<Promocion> crearPromocion(@Valid @RequestBody Promocion promocion) {
        return new ResponseEntity<>(promocionService.crearPromocion(promocion), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/eliminar/{id}")
    public ResponseEntity<Void> eliminarPromocion(@PathVariable Long id) {
        promocionService.eliminarPromocion(id);
        return ResponseEntity.noContent().build();
    }
}