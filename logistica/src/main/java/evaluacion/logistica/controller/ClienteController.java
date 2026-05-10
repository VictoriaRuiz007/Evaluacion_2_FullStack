package evaluacion.logistica.controller;

import evaluacion.logistica.model.Seguimiento;
import evaluacion.logistica.service.SeguimientoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/seguimientos/publico")
@Slf4j
public class ClienteController {

    @Autowired
    private SeguimientoService seguimientoService;

    @PostMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorID(@PathVariable int id){
        try{
            Seguimiento seguimiento = seguimientoService.buscarPorId(id);
            return ResponseEntity.ok(Map.of(
                    "mensaje","SEGUIMIENTO ENCONTRADO",
                    "detalle", seguimiento
            ));
        }
    }
}
