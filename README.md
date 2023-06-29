# 2.RDBMS
: RDBMS와 mybatis를 사용한 rest api 서버입니다. DB 유형에 따라 변경 사용할 수 있게 처리 하였습니다.

### # 구현 내용

#### 1. 프로필 별 DB 설정 : DB 타입에 따라 mapper 분기
  * dev, qa : postgresql
  * prod : mariadb
  
#### 2. log4jdbc 적용 
  콘솔에서 로그를 좀더 보기 쉽게 적용하였습니다.

#### 3. AOP transaction 처리
  서비스 단에서 오류 발생시 트랜잭션이 롤백됩니다.

#### 4. validation group 처리
  validation을 그룹단위로 지정하였습니다.

#### 5. 테스트 코드 예제 추가
  샘플 테스트 코드를 작성하였습니다.

#### 6. DB 암호화 처리

<hr/>

### # 사용방법

#### 1. DB 설치  
postgresql, mariadb가 설치 되어 있어야 합니다.

#### 2. DB 접속정보 추가 
application.properties에 db 접속정보를 넣고 연동시킬 db를 타입을 설정합니다. 암호화를 한다면 CryptUtilTest에 값을 넣고 
ENC(암호화값)을 넣어주면됩니다. db type은 postgresql, mariadb 이며 다른 RDBMS 필요시 DataConfig에 추가하고 사용하면 된다. 

```
spring.datasource.url=jdbc:log4jdbc:postgresql://localhost:5432/reodev
spring.datasource.username=ENC(ds3r4dhBq+7tGlmtvRns9Q==)
spring.datasource.password=ENC(ROXTIQXfIW4SCEhSIylNtbgcYnW+6eBZ)
#DB에 따라 mybatis 분기를 위해 타입을 정의
db.type=postgresql
```




