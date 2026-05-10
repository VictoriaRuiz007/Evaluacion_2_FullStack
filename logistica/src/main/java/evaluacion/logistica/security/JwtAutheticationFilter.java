package evaluacion.logistica.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAutheticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtAutheticationFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException{
            String authHeader = request.getHeader("Autorizacion");
        if (authHeader == null || !authHeader.startsWith(("Bearer "))) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);

        if(jwtService.esTokenValido(token)){
            String usuario = jwtService.extraerUsuario(token);

            UsernamePasswordAuthenticationToken autenticacion =
                    new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
            autenticacion.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(autenticacion);
        }
        filterChain.doFilter(request, response);
    }
}
