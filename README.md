# spring-jwt

-. 인증 메커니즘 중 하나인 JWT(Json Web Token)에 대한 테스트 프로젝트.

-. 토큰 발행, 검증, 갱신 3가지의 기능을 추상화하여 설계.

-. 테스트 가능한 restful api 구현.

-. JWT 구조

	(1) 헤더.바디.서명
	
	(2) base64(헤더).base64(바디).Hash Function(헤더.바디)
	
	(3) eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2UifQ.ipevRNuRP6HflG8cFKnmUPtypruRC4fb1DWtoLL62SY

-. 개발환경

	(1) JDK : 1.8.0_144
	
	(1) spring-boot-web : 1.5.9.RELEASE
	
	(2) jjwt : 0.7.0 (JWT 라이브러리)
	
-. 클래스 설명

	(1) TokenControl : token 생성, 검증, 갱신에 대해 테스트 가능한 restful API 게시.
	
	(2) TokenService : 토큰 메커니즘을 추상화한 인터페이스.
	
	(3) JwtTokenService : TokenService 구현체. JWT 토큰 메커니즘을 사용한 로직 구현.
	
	(4) TokenServiceResultEntity : TokenService 구현체 인터페이스에 대한 결과(=return) 데이터 모델.
	
-. 참고

	(1) https://github.com/jwtk/jjwt
	
	(2) https://jwt.io/introduction/