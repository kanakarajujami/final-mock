package com.nt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((csrf) -> csrf.disable());
        httpSecurity.authorizeHttpRequests(authorizeRequest -> {
            authorizeRequest.requestMatchers(HttpMethod.GET, "/department/api/**").permitAll()
                    .requestMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
                    .anyRequest().authenticated();
            try {
                httpSecurity.httpBasic(Customizer.withDefaults());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return  httpSecurity.build();

    }

    //create users

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        //create admin user
        UserDetails admin = User.builder().username("admin")
                .password(passwordEncoder().encode("admin")).build();

        //create customer

        UserDetails customer = User.builder().username("ramesh")
                .password(passwordEncoder().encode("ramesh")).build();
        return new InMemoryUserDetailsManager(admin,customer);
    }

    @Bean
    public  PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
