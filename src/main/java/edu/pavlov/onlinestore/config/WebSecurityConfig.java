package edu.pavlov.onlinestore.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


/**
 * The WebSecurityConfig class is used to configure the security of the
 * application.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * The securityFilterChain method is used to configure the security of the
     * application.
     * @param http The HttpSecurity object.
     * @return SecurityFilterChain
     */
    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) {

        //allow access to all endpoints
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests.anyRequest().permitAll()
        );

        return http.build();
    }

}
