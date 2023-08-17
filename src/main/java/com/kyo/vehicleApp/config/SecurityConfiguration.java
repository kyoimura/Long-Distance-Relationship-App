package com.kyo.vehicleApp.config;
import com.kyo.vehicleApp.services.CustomUserDetails;
import com.kyo.vehicleApp.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()
                )
                /*
                .authorizeRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )*/

                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/register").permitAll() // Allow access to /register without authentication
                        .anyRequest().authenticated()
                )

                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index", true).permitAll()
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true) // Invalidate the session on logout
                        .clearAuthentication(true) // Clear the authentication on logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Set the URL for triggering the logout process
                        .logoutSuccessUrl("/login").permitAll() // Redirect to "/login" after successful logout
                );

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}