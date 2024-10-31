package com.huysor.projectschool.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huysor.projectschool.constant.ApiConstant;
import com.huysor.projectschool.dto.response.ResultResp;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthentication jwtAuthentication;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return  http.csrf(AbstractHttpConfigurer::disable).cors(withDefaults()) .authorizeHttpRequests(author -> {
                    author.requestMatchers(ApiConstant.ApiLogin).permitAll();
                    author.requestMatchers(ApiConstant.ApiRegister).permitAll();
                    author.requestMatchers(ApiConstant.ImagePath+"**").permitAll();
                    author.requestMatchers("/api/categories/course/view/**").permitAll();
                    author.anyRequest().authenticated();
                }).exceptionHandling(ex->{
                    ex.authenticationEntryPoint((request, response, authException) -> {
                        ResultResp errorResponse = ResultResp.unauthorized();
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        response.setContentType("application/json");
                        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
                    });

                    ex.accessDeniedHandler((request, response, accessDeniedException) -> {
                        ResultResp errorResponse = ResultResp.of(
                                HttpStatus.FORBIDDEN.value(),
                                "Access denied: " + accessDeniedException.getMessage(),
                                null
                        );

                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        response.setContentType("application/json");
                        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
                    });
        }).userDetailsService(userDetailsService).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthentication, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs*/**");
    }
}
