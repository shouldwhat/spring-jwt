package com.wkkim.jwt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wkkim.jwt.service.TokenService;

@RestController
public class TokenControl {

	@Autowired
	private TokenService jwtTokenService;

	@RequestMapping(value = "/token/issue", method = RequestMethod.POST)
	public Map<String, Object> issueToken(@RequestBody Map<String, Object> body) {

		return jwtTokenService.issueToken(body);
	}

	@RequestMapping(value = "/token/{token}/verify", method = RequestMethod.POST)
	public Map<String, Object> verifyToken(@PathVariable String token) {

		return jwtTokenService.verifyToken(token);
	}
	
	@RequestMapping(value = "/token/{token}/refresh", method = RequestMethod.PUT)
	public Map<String, Object> refreshToken(@PathVariable String token) {
		
		return jwtTokenService.refreshToken(token);
	}
}
