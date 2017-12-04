package com.wkkim.jwt.model;

import java.util.Map;

public class TokenServiceResultEntity {
	
	private TokenServiceResultEnum resultCode;
	private Map<String, Object> resultData;
	
	@Override
	public String toString() {
		return "TokenServiceResultEntity [resultCode=" + resultCode + ", resultData=" + resultData + "]";
	}
	public TokenServiceResultEnum getResultCode() {
		return resultCode;
	}
	public void setResultCode(TokenServiceResultEnum resultCode) {
		this.resultCode = resultCode;
	}
	public Map<String, Object> getResultData() {
		return resultData;
	}
	public void setResultData(Map<String, Object> resultData) {
		this.resultData = resultData;
	}
}
