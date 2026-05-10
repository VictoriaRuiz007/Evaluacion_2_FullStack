package evaluacion.usuario.service;

import evaluacion.usuario.exception.AuthException;
import evaluacion.usuario.model.Usuario;
import evaluacion.usuario.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//METODOSSSSSS

    //crear usuario
    @Transactional
    public Usuario crearUsuario(Usuario usuario){
        log.info("Iniciando registro de nuevo usuario: {}", usuario.getCorreo());
        Optional<Usuario> existente = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (existente.isPresent()) {
            log.warn("Fallo. El correo {} ya existe", usuario.getCorreo());
            throw new AuthException("El correo ya está registrado en el sistema.");
        }
        String passwordCifrada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordCifrada);

        log.info("Usuario registrado exitosamente, id: {}", usuario.getId());
        return usuarioRepository.save(usuario);
    }

    //eliminar usuario
    @Transactional
    public void eliminarUsuario(Integer id) {
        log.info("Proceso de eliminacion de usuario, id: {}", id);
        if (!usuarioRepository.existsById(id)) {
            log.error("No puede eliminarse al usuario {}, No existe", id);
            throw new AuthException("El usuario con ID " + id + " no existe");
        }
        log.info("Usuario {} eliminado correctamente", id);
        usuarioRepository.deleteById(id);
    }

    //buscar por correo
    public Usuario buscarPorCorreo(String correo) {
        log.info("Buscando a usuario con correo: {}", correo);
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new AuthException("Usuario no encontrado"));
    }

   //mostrar usuarios

    public List<Usuario> mostrarUsuarios(){
        return usuarioRepository.findAll();
    }
}