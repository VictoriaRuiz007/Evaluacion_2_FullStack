package evaluacion.usuario.service;

import evaluacion.usuario.dto.LoginResponse;
import evaluacion.usuario.exception.AuthException;
import evaluacion.usuario.model.Usuario;
import evaluacion.usuario.repository.UsuarioRepository;
import evaluacion.usuario.security.JwtService;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    public LoginResponse login(String correo, String password) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new AuthException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(password)) {
            throw new AuthException("Contraseña incorrecta");
        }

        String token = jwtService.generarToken(usuario.getNombre(), usuario.getRol());
        return new LoginResponse("USUARIO AUTORIZADO", token, usuario.getNombre(), usuario.getApellido(), usuario.getRol());
    }

}
