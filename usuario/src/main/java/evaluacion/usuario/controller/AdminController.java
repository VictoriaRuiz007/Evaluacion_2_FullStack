package evaluacion.usuario.controller;

import evaluacion.usuario.model.Usuario;
import evaluacion.usuario.service.UsuarioService;
import org.hibernate.exception.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id){
        try{
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "USUARIO ELIMINADOOOOOOOOOOOOOOOOO",
                    "id_eliminado", id
            ));
        }
        catch(AuthException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", e.getMessage()));
        }
    }

    @GetMapping("/buscar/{correo}")
    public ResponseEntity<?> buscarPorCorreo(@PathVariable String correo ){
        try{
            Usuario usuario = usuarioService.buscarPorCorreo(correo);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "USUARIO ENCONRTRADOO",
                    "usuario", usuario
            ));
        }
        catch (AuthException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of( "mensaje", e.getMessage()));
        }
    }


    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> mostrarUsuarios(){
        usuarioService.mostrarUsuarios();
        return ResponseEntity.ok().build();
    }

}
