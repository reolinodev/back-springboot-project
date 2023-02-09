# 3.JWT

### # 구현 내용

#### 1. spring security 설치 및 jwt 적용

#### 2. 로그인 인증 구현

#### 3. rest api 테스트 코드 추가

#### 4. swagger와 jwt 연동 및 token 전역처리

<hr/>

### # 사용방법

#### 1. rest api 테스트 코드 사용법(intellij 기준) 
certification.http 실행해 accessToken을 얻은후 다른 api는 rest_api header 부분에 추가해준다.
```
{
  "header": {
    "requestUrl": "/api/certification",
    "message": "인증키가 생성되었습니다.",
    "resultCode": "ok",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZW9saW5vQGdtYWlsLmNvbSIsImxvZ2luX2lkIjoicmVvbGlub0BnbWFpbC5jb20iLCJ1c2VyX3B3IjoiMGZmZTFhYmQxYTA4MjE1MzUzYzIzM2Q2ZTAwOTYxM2U5NWVlYzQyNTM4MzJhNzYxYWYyOGZmMzdhYzVhMTUwYyIsInVzZXJfaWQiOiJVUzAwMDAwMDA3IiwidXNlcl9ubSI6InJlb2xpbm8iLCJleHAiOjE2NzQxMzA3NDYsImlhdCI6MTY3NDExNjM0Nn0.4RfEKV9YjxNoZSAx6lfkazCsfB1-npGJYcrwX3nO3Nk",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZW9saW5vQGdtYWlsLmNvbSIsImxvZ2luX2lkIjoicmVvbGlub0BnbWFpbC5jb20iLCJ1c2VyX3B3IjoiMGZmZTFhYmQxYTA4MjE1MzUzYzIzM2Q2ZTAwOTYxM2U5NWVlYzQyNTM4MzJhNzYxYWYyOGZmMzdhYzVhMTUwYyIsInVzZXJfaWQiOiJVUzAwMDAwMDA3IiwidXNlcl9ubSI6InJlb2xpbm8iLCJleHAiOjE2NzQyMDI3NDYsImlhdCI6MTY3NDExNjM0Nn0.2NOHK9yp5LZ79Jz4seTWi7HxbxZ88vx6i5kIYJZikJI"
  }
}

```

#### 2. security 예외 처리 방법
Constants.java에 예외처리할 url을 입력한다. 도메인과 리소르를 분리했다.
```
    public static final String[] resourceArray = new String[] {
        "/page/**",
        "/dist/**",
        "/lib/**",
        "/favicon.ico"
    };

    public static final String[] permitAllArray = new String[] {
        "/",
        "/api/certification",
        "/swagger-ui/**",
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**"
    };
```

#### 3. swagger에서 jwt 전역인증하기
1. /api/certification 실행해서 accessToken 복사
2. swagger-ui 화면에서 Authorize 선택해서 jwt value 값에 accessToken 넣기
3. 다른 api 실행



