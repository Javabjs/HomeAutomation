package com.homeautomation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.homeautomation.security.jwtutils.JwtAuthenticationEntryPoint;
import com.homeautomation.security.jwtutils.JwtAuthenticationFilter;
import com.homeautomation.security.service.UserDetailsServiceImpl;





@Configuration
public class SecurityConfig {
	

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtAuthenticationFilter filter;

	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	public JwtAuthenticationEntryPoint authenticationJwtTokenFilter() {
		return new JwtAuthenticationEntryPoint();
	}
	

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
		@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
			return config.getAuthenticationManager();
		}

		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

			http.csrf(csrf->csrf.disable())
			.cors(cors->cors.disable())



			  .authorizeHttpRequests(auth ->
	          auth.requestMatchers("/auth/**","/role/**","/user/**").permitAll()
					  .requestMatchers("/user").hasAnyAuthority("USER","ADMIN")
					  .requestMatchers("/admin").hasAuthority("ADMIN")
					  .requestMatchers("/api/v1/roledemo/**").authenticated()
					  .anyRequest().authenticated())

					.exceptionHandling(ex->ex.authenticationEntryPoint(point))
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);




			return http.build();

		}

  }


