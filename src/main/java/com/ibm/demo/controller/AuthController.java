package com.ibm.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.security.JwtUtil;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;

	public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil) {
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}

	// POST /api/v1/auth/login
	// Body: { "username": "admin", "password": "admin123" }
	// Returns: { "token": "eyJ..." }
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(body.get("username"), body.get("password")));

		String token = jwtUtil.generateToken(body.get("username"));
		return ResponseEntity.ok(Map.of("token", token));
	}
}