package Evaluacion.Catalogo.controller;

import Evaluacion.Catalogo.model.Videojuego;
import Evaluacion.Catalogo.service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {

    @Autowired
    private VideojuegoService service;

    @GetMapping("/publico")
    public List<Videojuego> listarTodos() {
        return service.listarVideojuegos();
    }

    @GetMapping("/publico/buscar")
    public Videojuego buscarPorID(@RequestParam Long id) {
        return service.buscarPorID(id);
    }

    @PostMapping("/admin/crear")
    public Videojuego crearVideojuego(@RequestBody Videojuego videojuego) {
        return service.crearVideojuego(videojuego);
    }

    @DeleteMapping("/admin/eliminar")
    public ResponseEntity<?> eliminarVideojuego(@RequestParam Long id) {
        service.eliminarVideojuego(id);
        return ResponseEntity.ok().build();
    }
}