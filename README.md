# 2.RDBMS

### # 구현 내용

#### 1. 프로필 별 DB 설정 : DB 타입에 따라 mapper 분기
  * dev, qa : postgresql
  * prod : mariadb
  
#### 2. log4jdbc 적용

#### 3. AOP transaction 처리

#### 4. validation group 처리

#### 5. 테스트 코드 예제 추가

#### 6. DB 암호화 처리

<hr/>

### # 사용방법

#### 1. DB 설치  
postgresql, mariadb가 설치 되어 있어야 한다.

#### 2. DB 접속정보 추가 
application.properties에 db 접속정보를 넣고 연동시킬 db를 타입을 설정한다. 암호화를 한다면 CryptUtilTest에 값을 넣고 
ENC(암호화값)을 넣어주면 된다. db type은 postgresql, mariadb 이며 다른 RDBMS 필요시 DataConfig에 추가하고 사용하면 된다. 

```
spring.datasource.url=jdbc:log4jdbc:postgresql://localhost:5432/reodev
spring.datasource.username=ENC(ds3r4dhBq+7tGlmtvRns9Q==)
spring.datasource.password=ENC(ROXTIQXfIW4SCEhSIylNtbgcYnW+6eBZ)
#DB에 따라 mybatis 분기를 위해 타입을 정의
db.type=postgresql
```




