package org.sr.jwt;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.sr.exception.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.xml.bind.DatatypeConverter;

@Component
public class JwtUtil {
	private static final String SECRET_KEY = "123456789098765432109876543qwertyuiopoiuytrewqqasdfghjklkmnbvcxzasdfghjhytrewsedfghjuytrfdcfvbhgtfdcvghf";
	private final long validityInMilliseconds = 3600000; // 1 hour
	private final String issuer = "BajajFinServ";
	private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	public String generateToken(UserDetails userDetails) {
		System.out.println("JwtUtil.generateToken()");
		Instant currentInstant = Instant.now();
		Date currentDate = Date.from(currentInstant);

		Map<String, Object> claims = new HashMap<>();
		claims.put("userName", userDetails.getUsername());
		claims.put("password", userDetails.getPassword());

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
		Key signKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setClaims(claims).setIssuedAt(currentDate).setIssuer(issuer)
				.setSubject(userDetails.getUsername()).signWith(signKey);

		if (validityInMilliseconds > 0) {
			Date expirationDate = new Date(currentDate.getTime() + validityInMilliseconds);
			builder.setExpiration(expirationDate);
		}

		return builder.compact();
	}

	public String extractUsername(String token) {
		System.out.println("JwtUtil.extractUsername()");

		String userName = null;
		try {
			userName = Jwts.parserBuilder().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).build()
					.parseClaimsJws(token).getBody().getSubject();
		} catch (Exception e) {
			throw new InvalidTokenException("Invalid Token", HttpStatus.BAD_REQUEST);
		}
		return userName;
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		System.out.println("JwtUtil.validateToken()");

		Claims claims = Jwts.parserBuilder().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).build()
				.parseClaimsJws(token).getBody();

		// Validate the AuthTokenEntity object
		if (userDetails.getUsername() == null || userDetails.getPassword() == null
				|| !userDetails.getUsername().equals(claims.get("userName"))
				|| !userDetails.getPassword().equals(claims.get("password"))) {
			return false;
		}
		return true;
	}
}
