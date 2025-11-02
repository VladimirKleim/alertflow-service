package com.kleim.alertflow.security;

import com.kleim.alertflow.security.token.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfiguration(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->

                        auth.requestMatchers(HttpMethod.POST, "/location").hasAuthority("GUEST")
                                .requestMatchers(HttpMethod.GET, "/location").hasAuthority("GUEST")
                                .requestMatchers(HttpMethod.GET, "/location/{locationId}").hasAuthority("GUEST")
                                .requestMatchers(HttpMethod.DELETE, "/location/{locationId}").hasAuthority("GUEST")




                         .requestMatchers(HttpMethod.POST, "/user").permitAll()
                         .requestMatchers(HttpMethod.POST, "/user/auth").permitAll()
                         .requestMatchers(HttpMethod.GET, "/user").hasAuthority("LEAD")


                                .anyRequest().permitAll())

                .addFilterBefore(jwtTokenFilter, AnonymousAuthenticationFilter.class)

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.debug(true).ignoring()
//                .requestMatchers("/css/**",
//                        "/js/**",
//                        "/img/**",
//                        "/lib/**",
//                        "/favicon.ico",
//                        "/swagger-ui/**",
//                        "/v2/api-docs",
//                        "/v3/api-docs",
//                        "/configuration/ui",
//                        "/swagger-resources/**",
//                        "/configuration/security",
//                        "/swagger-ui.html",
//                        "/webjars/**",
//                        "/v3/api-docs/swagger-config",
//                        "/openapi.yaml"
//                );
//    }
}
