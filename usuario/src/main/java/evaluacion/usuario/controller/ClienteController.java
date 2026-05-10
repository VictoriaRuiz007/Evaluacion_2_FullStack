package evaluacion.usuario.controller;

import evaluacion.usuario.model.Usuario;
import evaluacion.usuario.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios/publico")
@Slf4j
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCliente(@RequestBody Usuario usuario){
        log.info("Recibiendo peticion de registro de {}", usuario.getCorreo());
        try{
            usuario.setRol("cliente");
            Usuario nuevoUser = usuarioService.crearUsuario(usuario);
            log.info("Usuario creado correctamente con id {}", usuario.getId());
            return ResponseEntity.status((HttpStatus.CREATED))
                    .body(Map.of(
                            "mensaje","CUENTA CREADA",
                            "correo", nuevoUser.getCorreo(),
                            "nombre", nuevoUser.getNombre()
                    ));
        }
        catch (AuthException e){
            log.error("Error en el registro");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensaje", e.getMessage()));
        }

    }
}
