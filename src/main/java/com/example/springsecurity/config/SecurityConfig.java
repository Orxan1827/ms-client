package com.example.springsecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.springsecurity.config.UserRoles.ADMIN;
import static com.example.springsecurity.config.UserRoles.STUDENT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordConfig passwordConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "port", "/api/clients/main")
                .permitAll()// Allow public access
                .antMatchers("/api/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails orxan = User.builder()
                .username("Orxan")
                .password(passwordConfig.passwordEncoder().encode("o12345"))
                .roles(ADMIN.name())
                .build();

        UserDetails simare = User.builder()
                .username("Simare")
                .password(passwordConfig.passwordEncoder().encode("s12345"))
                .roles(STUDENT.name())
                .build();
        return new InMemoryUserDetailsManager(orxan, simare);
    }
}
