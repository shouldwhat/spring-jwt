package com.wkkim.jwt.service;

import java.util.Map;

public interface TokenService<T> {

	public T issueToken(Map<String, Object> param);
	
	public T verifyToken(String token);
	
	public T refreshToken(String token);
}
