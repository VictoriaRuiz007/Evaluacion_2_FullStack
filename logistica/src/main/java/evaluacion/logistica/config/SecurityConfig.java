package evaluacion.logistica.config;

import evaluacion.logistica.security.JwtAutheticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAutheticationFilter jwtAutheticationFilter;

    public SecurityConfig(JwtAutheticationFilter jwtAutheticationFilter) {
        this.jwtAutheticationFilter = jwtAutheticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/seguimientos/publico/**").permitAll()
                        .requestMatchers("/api/seguimientos/admin/**").hasRole("admin")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException)-> {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.setContentType("application/json");
                            response.getWriter().write("""
                                    {
                                      "estado": 401,
                                      "error": "Unathorized",
                                      "mensaje": "TOKEN AUSENTE O INVALIDO",
                                      "ruta": "%s"
                                    }
                                    """.formatted(request.getRequestURI()));
                        })
                );
        http.addFilterBefore(jwtAutheticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}