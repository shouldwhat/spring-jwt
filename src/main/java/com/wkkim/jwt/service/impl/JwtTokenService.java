package com.wkkim.jwt.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wkkim.jwt.service.TokenService;

@Service("jwtTokenService")
public class JwtTokenService implements TokenService {

	private static final Logger LOG = LoggerFactory.getLogger(JwtTokenService.class);

	@Override
	public Map<String, Object> issueToken(Map<String, Object> param) {

		LOG.info("========================= issueToken");
		return null;
	}

	@Override
	public Map<String, Object> verifyToken(String token) {

		LOG.info("========================= verifyToken");
		return null;
	}

	@Override
	public Map<String, Object> refreshToken(String token) {

		LOG.info("========================= refreshToken");
		return null;
	}

}
