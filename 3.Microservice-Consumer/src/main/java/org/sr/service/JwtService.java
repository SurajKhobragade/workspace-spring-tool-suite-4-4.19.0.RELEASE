package org.sr.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sr.bean.AuthenticationRequest;
import org.sr.bean.AuthenticationResponse;

@FeignClient("JWT-SERVICE")
public interface JwtService {

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception;

	@GetMapping("/admin/some-api")
	public String getAdmin();

	@GetMapping("/user/some-api")
	public String getUser();

	@GetMapping("/public-api")
	public String getPublicAccess();

}
