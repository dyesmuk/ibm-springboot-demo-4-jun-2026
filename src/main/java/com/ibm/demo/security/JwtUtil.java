package com.ibm.demo.security;

import java.util.Date;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	private static final String SECRET = "ibm-demo-secret-key-must-be-at-least-256-bits-long";
	private static final long EXPIRY = 86400000L; // 24 hours

	private SecretKey key() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	public String generateToken(String username) {
		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRY)).signWith(key()).compact();
	}

	public String extractUsername(String token) {
		return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().getSubject();
	}

	public boolean isValid(String token, String username) {
		try {
			return extractUsername(token).equals(username) && !Jwts.parser().verifyWith(key()).build()
					.parseSignedClaims(token).getPayload().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
}