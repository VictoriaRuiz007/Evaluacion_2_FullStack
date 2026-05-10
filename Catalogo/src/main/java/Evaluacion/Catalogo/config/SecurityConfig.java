package Evaluacion.Catalogo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/catalogo/publico/**").permitAll()
                        .requestMatchers("/api/catalogo/admin/**").hasRole("admin")
                        .anyRequest().authenticated()
                );

        // Aquí se debe añadir el filtro: .addFilterBefore(...)
        return http.build();
    }
}