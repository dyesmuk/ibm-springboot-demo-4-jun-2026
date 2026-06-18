package com.ibm.demo.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ibm.demo.security.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtUtil jwtUtil;

	public SecurityConfig(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	// admin / admin123 → ROLE_ADMIN (GET + POST + PUT + DELETE)
	// user / user123 → ROLE_USER (GET only)
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		return new InMemoryUserDetailsManager(
				User.withUsername("admin").password(encoder.encode("admin123")).roles("ADMIN").build(),
				User.withUsername("user").password(encoder.encode("user123")).roles("USER").build());
	}

	private OncePerRequestFilter jwtFilter(UserDetailsService uds) {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
					throws ServletException, IOException {

				String header = req.getHeader("Authorization");
				if (header != null && header.startsWith("Bearer ")) {
					String token = header.substring(7);
					String username = jwtUtil.extractUsername(token);
					if (username != null && SecurityContextHolder.getContext().getAuthentication() == null
							&& jwtUtil.isValid(token, username)) {
						var userDetails = uds.loadUserByUsername(username);
						SecurityContextHolder.getContext().setAuthentication(
								new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities()));
					}
				}
				chain.doFilter(req, res);
			}
		};
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService uds) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/v1/**").authenticated()
						.requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN").anyRequest().authenticated())
				.addFilterBefore(jwtFilter(uds), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}