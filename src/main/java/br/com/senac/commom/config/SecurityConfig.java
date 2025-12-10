package br.com.senac.commom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS,  "/**").permitAll()
                        // Rotas de Login
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        // Rotas para ESTÁGIO DE DEV
                        .requestMatchers(HttpMethod.GET, "/devstage").permitAll()
                        .requestMatchers(HttpMethod.POST, "/devstage").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/devstage/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/devstage/{id}").hasRole("ADMIN")
                        // Rotas para AREA DE ATUAÇÃO
                        .requestMatchers(HttpMethod.GET, "/activityarea").permitAll()
                        .requestMatchers(HttpMethod.POST, "/activityarea").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/activityarea/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/activityarea/{id}").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Listar todos os projetos não precisa estar logado
                        .requestMatchers(HttpMethod.GET, "/projects").permitAll()
                        .requestMatchers(HttpMethod.GET, "/projects/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/uploads/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}