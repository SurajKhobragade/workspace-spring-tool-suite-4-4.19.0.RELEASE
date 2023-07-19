package org.sr.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sr.bean.AuthenticationRequest;
import org.sr.bean.AuthenticationResponse;
import org.sr.config.CustomFeignInterceptor;
import org.sr.service.JwtService;

@RefreshScope
@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	private JwtService jwtService;

//	@Value("${server.port}")
//	private String serverPort;

	@Value("${my.file}")
	private String file;

	@Value("${my.app}")
	private String app;

	@Value("${my.profile}")
	private String profile;

	@Autowired
	private CustomFeignInterceptor feignInterceptor;

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> loggedIn(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		AuthenticationResponse response = jwtService.login(authenticationRequest);
//		response.setPort(serverPort);

		System.out.println("File is:  " + file);
		System.out.println("App is:  " + app);
		System.out.println("Profile is:  " + profile);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/adminCheck")
	public String checkAdmin(@RequestHeader("Authorization") String token) throws Exception {

		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", token);

		feignInterceptor.setHeaders(headers);
		return jwtService.getAdmin();
	}

	@GetMapping("/userCheck")
	public String checkUser(@RequestHeader("Authorization") String token) {
		return jwtService.getUser();
	}

	@GetMapping("/publicCheck")
	public String checkPublicAccess(@RequestHeader("Authorization") String token) {
		return jwtService.getPublicAccess();
	}

}
