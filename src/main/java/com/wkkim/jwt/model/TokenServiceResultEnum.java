package com.wkkim.jwt.model;

import java.util.Map;

public enum TokenServiceResultEnum {
	SUCCESS,
	VIOLATED_TOKEN,
	EXPIRED_TOKEN,
	UNKNOWN_ERROR,
	;
	
	public TokenServiceResultEntity toResultEntity(Map<String, Object> resultData) {
		
		TokenServiceResultEntity resultEntity = new TokenServiceResultEntity();
		resultEntity.setResultCode(this);
		resultEntity.setResultData(resultData);
		
		return resultEntity;
	}
}
