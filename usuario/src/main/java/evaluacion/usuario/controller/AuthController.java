package evaluacion.usuario.controller;

import evaluacion.usuario.dto.LoginRequest;
import evaluacion.usuario.dto.LoginResponse;
import evaluacion.usuario.exception.AuthException;
import evaluacion.usuario.security.JwtService;
import evaluacion.usuario.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request.getCorreo(), request.getPassword());
            String redirectUrl = response.getRol().equalsIgnoreCase("admin") ? "/api/usuarios/admin/**" : "/api/usuarios/publico/**";

            Map<String, Object> body = new HashMap<>();
            body.put("token", response.getToken());
            body.put("mensaje", response.getMensaje());
            body.put("rol", response.getRol());
            body.put("redirect", redirectUrl);

            return ResponseEntity.ok(body);

        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("mensaje", e.getMessage()));
        }
    }
}
