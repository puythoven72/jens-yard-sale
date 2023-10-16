package com.Uypiren.jensyardsale.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableJpaAuditing

@CrossOrigin(origins = "http://localhost:3000")
public class SecurityConfiguration {


    @Autowired
    private final UserAuthProvider userAuthProvider;

    public static final String[] ENDPOINTS_WHITELIST = {
            "/css/**",
            "/",
            "/api/user/registerSave",
            "api/items",
            "api/items/itemsByCategory/**",
            "/api/user/login",
            "api/items/image-admin/primaryImage/**",
            "api/items/drop-down-selections/**"
    };

    public static final String LOGIN_URL = "/api/user/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String DEFAULT_SUCCESS_URL = "/home";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";


    @Bean
    public static PasswordEncoder passwordEncoder() {
        System.out.println("DO I GET HERE2");
        return new BCryptPasswordEncoder();

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("CHECKING YOUR SECURITY?");

        http.csrf(csrf -> csrf.disable())

                .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class).
                authorizeHttpRequests(request ->
                        request.requestMatchers(ENDPOINTS_WHITELIST).permitAll()
                                .anyRequest().authenticated()).
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                httpBasic(withDefaults());

        http.cors();
        return http.build();



    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        System.out.println("CORS CAN YOU HEAR ME");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","DELETE","PUT"));

        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditAwareImpl();
    }

}