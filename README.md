# 1.BASIC

### # 구현 내용
#### 1. AOP 적용
- service 실행시간 체크
- 파라미터 및 리턴값 로그 적용

#### 2. Exception 처리
- Valid 위반시

#### 3. Swagger 적용
- RestConteroller만 적용되게 설정

#### 4. Spring profile 설정
- dev, qa, prod로 분기

#### 5. Response 형태 설정
- data, header

#### 6. 외부 API 호출 예제
- Naver 검색 Api

<hr/>

### # 사용방법
#### 1. profile 적용법
: application.properties 변경(dev, qa, prod)
```
spring.profiles.active=dev
```
#### 2. lombok을 정상적으로 사용하려면 사용하는 ide 플러그인을 설치할 것

#### 3. maven으로 dependencies 다운로드 받은 이후에 기동 