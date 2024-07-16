package software_libre.api_luna.config;

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
import software_libre.api_luna.security.JwtAuthenticationFilter;
import software_libre.api_luna.security.UserDetailsServiceImp;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImp userDetailsServiceImp;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsServiceImp userDetailsServiceImp,
                          JwtAuthenticationFilter jwtAuthenticationFilter
                          ) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(c -> c.disable())//csrf no lo necesitamos al ser puro back
                .sessionManagement((s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)))//Control de sesion, no guardamos el estado ya que vamos a trabajar con token
                .authorizeHttpRequests(h -> {
                    h.requestMatchers(HttpMethod.POST, "/authorization/login").permitAll();
                    h.requestMatchers(HttpMethod.POST, "/authorization/register").permitAll();//TODO: si el controlador no crece mas lo dejamos como/* y un solo filtro
                    h.requestMatchers(HttpMethod.GET, "/test").hasRole("admin");
                    h.anyRequest().authenticated();
                })
                .userDetailsService(userDetailsServiceImp)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
