package com.ibm.demo.security;

import java.util.Date;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	private static final String SECRET = "ibm-demo-secret-key-must-be-at-least-256-bits-long";
	private static final long EXPIRY = 86400000L; // 24 hours

	private SecretKey key() {
		LOG.info("Step 8, 10 key");
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	public String generateToken(String username) {
		LOG.info("Step 7 - generateToken");

		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRY)).signWith(key()).compact();
	}

	public String extractUsername(String token) {
		LOG.info("Step 9 - extractUsername");
		return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().getSubject();
	}

	public boolean isValid(String token, String username) {
		LOG.info("Step 11 - isValid");
		try {
			return extractUsername(token).equals(username) && !Jwts.parser().verifyWith(key()).build()
					.parseSignedClaims(token).getPayload().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
}
