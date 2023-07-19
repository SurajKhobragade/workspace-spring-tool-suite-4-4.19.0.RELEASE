package org.sr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.sr.bean.AuthenticationRequest;
import org.sr.bean.AuthenticationResponse;
import org.sr.jwt.JwtUtil;
import org.sr.service.UserDetailsServiceImpl;

@RefreshScope
@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Value("${server.port}")
	private String serverPort;

	@Value("${my.app}")
	private String app;

	@Value("${my.profile}")
	private String profile;

	@Value("${my.jwt}")
	private String file;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt, serverPort + app + " : " + profile + " : " + file));
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/admin/some-api")
	public ResponseEntity<?> adminOnlyApi() {
		System.out.println("AuthenticationController.adminOnlyApi()");

		return ResponseEntity.ok("Got admin access AND the Port no is : " + serverPort);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/user/some-api")
	public ResponseEntity<?> userApi() {
		System.out.println("AuthenticationController.userApi()");
		return ResponseEntity.ok("Got User access AND the Port no is : " + serverPort);
	}

	@GetMapping("/public-api")
	public ResponseEntity<?> publicApi() {
		System.out.println("AuthenticationController.publicApi()");
		return ResponseEntity.ok("Anyone access AND the Port no is : " + serverPort);
	}

}
