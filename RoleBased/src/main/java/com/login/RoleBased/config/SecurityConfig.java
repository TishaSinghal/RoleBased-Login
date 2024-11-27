package com.login.RoleBased.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.login.RoleBased.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	 private final CustomUserDetailsService customUserDetailsService;

	    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
	        this.customUserDetailsService = customUserDetailsService;
	    }

    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        return http.csrf(customizer -> customizer.disable()).
//                authorizeHttpRequests(request -> request.anyRequest().authenticated()).
//                httpBasic(Customizer.withDefaults()).
//                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

    	   http
           .csrf(csrf -> csrf.disable())
           .authorizeHttpRequests(authorizeRequests ->
               authorizeRequests
                   .requestMatchers("/admin/**").hasRole("ADMIN")
                   .requestMatchers("/client/**").hasRole("CLIENT")
                   .requestMatchers("/director/**").hasAnyRole("CLIENT", "DIRECTOR")
                   .requestMatchers("/hod/**").hasAnyRole("DIRECTOR", "HOD")
                   .requestMatchers("/placementHead/**").hasAnyRole("DIRECTOR", "PLACEMENT_HEAD")
                   .anyRequest().authenticated()
           )
           .formLogin(formLogin -> formLogin
                   .loginPage("/login")  // Specify custom login page if you have one, or use the default
                   .permitAll()  // Allow everyone to access the login page
               )
               .httpBasic(httpBasic -> httpBasic
                   .realmName("MyApp Realm") // Customize your HTTP Basic authentication realm if needed
               );

       return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}