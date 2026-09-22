package com.rajtechnologies.springbootsecurityinmemorycredentials.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails u1 = User.withDefaultPasswordEncoder()
                .username("obul")
                .password("reddy")
                .build();
        UserDetails u2 = User.withDefaultPasswordEncoder()
                .username("vijaya")
                .password("lakshmi")
                .build();
        return new InMemoryUserDetailsManager(u1, u2);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(req -> req.requestMatchers("/api/welcome")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults()).build();
    }

}
