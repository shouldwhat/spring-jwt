package com.wkkim.jwt.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.wkkim.jwt.model.TokenServiceResultEntity;
import com.wkkim.jwt.model.TokenServiceResultEnum;
import com.wkkim.jwt.service.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Service("jwtTokenService")
public class JwtTokenService implements TokenService<TokenServiceResultEntity> {

	private static final long TOKEN_ALIVE = 1000L * 60L; /* (1sec * 3600) */
	
	private Key encKey;
	
	@PostConstruct
	public void onCreate() {
		encKey = MacProvider.generateKey();
	}
	
	@Override
	public TokenServiceResultEntity issueToken(Map<String, Object> param) {

		Map<String, Object> header = (Map<String, Object>) param.get("header");
		Map<String, Object> body = (Map<String, Object>) param.get("body");
		
		return this.issueToken(header, body);
	}
	
	public TokenServiceResultEntity issueToken(Map<String, Object> header, Map<String, Object> body) {
		
		long now = 0L;
		long expiration = 0L;
		{	/* [Step.1] Set 'token issued Time' and 'expired Time' */
			now = System.currentTimeMillis();
			expiration = now + TOKEN_ALIVE;
		}
		
		/* The bits of information encoded in the body of a JWT are called claims. */
		Claims customJwtBody = new DefaultClaims(body);
		{	/* [Step.2] makeup Jwt Custom Body (=Claim) */
			customJwtBody.setIssuedAt(new Date(now));
			customJwtBody.setExpiration(new Date(expiration));
		}
		
		String issuedToken = null;
		{	/* [Step.3] generate JWT token */
			issuedToken = Jwts.builder().setHeader(header)
										.setClaims(customJwtBody)
										.signWith(SignatureAlgorithm.HS512, encKey)
										.compact();
		}
		
		Map<String, Object> resultData = new HashMap<>();
		{	/* [Step.4] makeup token generation result */
			resultData.put("token", issuedToken);
			resultData.put("expireUnixTime", expiration);
		}
		
		return TokenServiceResultEnum.SUCCESS.toResultEntity(resultData);
	}

	@Override
	public TokenServiceResultEntity verifyToken(String token) {

		TokenServiceResultEnum verifyResultEnum = TokenServiceResultEnum.SUCCESS;
		
		try {
			Jwts.parser().setSigningKey(this.encKey).parseClaimsJws(token).getBody();
		}
		catch(SignatureException e) {	/* 토큰 해쉬 변조 됨. */
//			e.printStackTrace();
			verifyResultEnum = TokenServiceResultEnum.VIOLATED_TOKEN;
		}
		catch(ExpiredJwtException e) {	/* 토큰 유효 시간 만료 됨. */
//			e.printStackTrace();
			verifyResultEnum = TokenServiceResultEnum.EXPIRED_TOKEN;
		}
		catch(Exception e) {			/* 알 수 없는 오류. */
//			e.printStackTrace();
			verifyResultEnum = TokenServiceResultEnum.UNKNOWN_ERROR;
		}
		
		return verifyResultEnum.toResultEntity(null);
	}

	@Override
	public TokenServiceResultEntity refreshToken(String token) {

		TokenServiceResultEntity verifyResult;
		{	/* [Step.1] before token refresh, do verify token.*/
			verifyResult = this.verifyToken(token);
			if( verifyResult.getResultCode().equals(TokenServiceResultEnum.SUCCESS) == false ) {
				return verifyResult;
			}
		}
		
		JwsHeader<?> header;
		Claims body;
		{	/* [Step.2] get orignal datas('header', 'body') in token */
			Jws<Claims> parsedJwtToken = Jwts.parser().setSigningKey(this.encKey).parseClaimsJws(token);
			
			header = parsedJwtToken.getHeader();
			body = parsedJwtToken.getBody();
		}
		
		return this.issueToken(header, body);
	}
	
}
