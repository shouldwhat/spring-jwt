package com.wkkim.jwt.service;

import java.util.Map;

public interface TokenService {

	public Map<String, Object> issueToken(Map<String, Object> param);
	
	public Map<String, Object> verifyToken(String token);
	
	public Map<String, Object> refreshToken(String token);
}
