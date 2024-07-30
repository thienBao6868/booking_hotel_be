
package com.Thienbao.booking.config;
import com.Thienbao.booking.filter.CustomFilterSecurity;
import com.Thienbao.booking.security.CustomAuthenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, CustomAuthenProvider customAuthenProvider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, ApiProperties apiProperties, CustomFilterSecurity customFilterSecurity) throws Exception {
        String basePath = apiProperties.getBasePath();
        return http
                .csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
            .cors(withDefaults())



                .authorizeHttpRequests(author -> {
                    author.requestMatchers( basePath + "/auth/login").permitAll();
//                    author.requestMatchers(basePath+"/admin/**").hasRole("ADMIN");
                    author.requestMatchers(basePath+"/admin/**").permitAll();
                    author.requestMatchers(basePath+"/hotel/me",basePath+"/review/reply", basePath + "/review/hotelier/**", basePath + "/booking/hotel/**").permitAll();
                    author.requestMatchers(basePath+ "/hotel/all",basePath +"/hotel/{id}").permitAll();
//                     author.requestMatchers("/hotel/all").permitAll();
                    author.requestMatchers(basePath+ "/user").permitAll();
                    author.requestMatchers(HttpMethod.POST,basePath+"/user/signup").permitAll();
                    author.anyRequest().authenticated();
                })
                .addFilterBefore(customFilterSecurity, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500")); // Allow your frontend's origin
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
