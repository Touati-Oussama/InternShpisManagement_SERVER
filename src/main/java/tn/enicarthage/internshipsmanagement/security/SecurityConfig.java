package tn.enicarthage.internshipsmanagement.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tn.enicarthage.internshipsmanagement.entities.ERole;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        /************ Login & Sign up ******************/
                        request.requestMatchers("/api/v1/auth/**").permitAll().
                        /*********** Salle
                        requestMatchers(HttpMethod.POST, "/api/v1/salle/**").hasRole(ERole.DIRECTION.name()).
                        requestMatchers(HttpMethod.DELETE, "/api/v1/salle/**").hasRole(ERole.DIRECTION.name()).
                         ******************************/
                         anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider).addFilterBefore(
                        jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/admin/**").permitAll() // Autoriser les requêtes GET sous /api/v1/admin/**
                .antMatchers(HttpMethod.POST, "/api/v1/admin/**").hasRole("ADMIN") // Exiger le rôle ADMIN pour les requêtes POST sous /api/v1/admin/**
                .anyRequest().authenticated() // Pour toutes les autres URL, l'authentification est requise
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }*/
}
