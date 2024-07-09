package com.discovery.spring_security.security;

import com.discovery.spring_security.service.iml.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    DataSource dataSource;

    private final CustomUserService customUserService;

    public SecurityConfig(DataSource dataSource, CustomUserService customUserService) {
        this.dataSource = dataSource;
        this.customUserService = customUserService;
    }

    @Bean
    SecurityFilterChain CustomSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests)->{
            requests.requestMatchers("/h2-console/**").permitAll();
            requests.requestMatchers("/admin/**").hasRole("ADMIN");
            requests.requestMatchers("/user**").permitAll();
            requests.anyRequest().authenticated();

        });

        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(headers->
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());


        return http.build();
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }










//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user1 =User.withUsername("user")
//                .password("{noop}usePassword")
//                .roles("USER")
//                .build();
//
////        JdbcUserDetailsManager userDetailsManager
////                = new JdbcUserDetailsManager(dataSource);
////        userDetailsManager.createUser(user1);
////
////        return userDetailsManager;
//        return new InMemoryUserDetailsManager(user1);
//    }



}
