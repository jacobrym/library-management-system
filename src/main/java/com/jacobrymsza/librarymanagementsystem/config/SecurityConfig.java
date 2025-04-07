package com.jacobrymsza.librarymanagementsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security settings in the library management system.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  @Value("${USER_USERNAME}")
  private String userUsername;

  @Value("${USER_PASSWORD}")
  private String userPassword;

  @Value("${ADMIN_USERNAME}")
  private String adminUsername;

  @Value("${ADMIN_PASSWORD}")
  private String adminPassword;

  /**
   * Configures the security filter chain for HTTP requests.
   *
   * @param http the HttpSecurity object to configure
   * @return the configured SecurityFilterChain
   * @throws Exception if an error occurs during configuration
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/login", "/css/**", "/webjars/**").permitAll()
            .requestMatchers("/books", "/books/{id}", "/authors", "/authors/{id}",
                             "/borrowings", "/borrowings/new",
                             "/borrowings/{id}/return").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/users/**", "/books/new", "/authors/new").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/books", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        );

    return http.build();
  }

  /**
   * Provides an in-memory user details service for testing purposes.
   *
   * @return the UserDetailsService with predefined users
   */
  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username(userUsername)
        .password(userPassword)
        .roles("USER")
        .build();
    UserDetails admin = User.withDefaultPasswordEncoder()
        .username(adminUsername)
        .password(adminPassword)
        .roles("ADMIN")
        .build();
    return new InMemoryUserDetailsManager(user, admin);
  }
}
