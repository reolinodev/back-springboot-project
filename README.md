# Springboot BackEnd Project

## # 내용
SpringBoot를 사용한 기본적인 백엔드 코드입니다. <br/>
프로젝트의 상황에 따라 각 Branch에서 Checkout 해서 쓰기 위해 만들었습니다. 상세 구현 내용은 각 Branch에 기재 하였습니다.

## # Branch별 유의사항
Maven 프로젝트인 만큼 pom.xml에 있는 라이브러리를 다운로드 받아야 합니다. package.json이 있는 branch인 경우 npm을 통해 module을 다운로드 받아야 합니다.

## # 각 Branch 및 설명 (JPA 버전은 타 프로젝트라 따로 받으셔야 합니다.)

- [1.BASIC](https://github.com/reolinodev/back-springboot-project/tree/1.BASIC)
  : 스프링부트의 기본 버전입니다. 간단하게 구현된 rest api입니다.

- [2.RDBMS](https://github.com/reolinodev/back-springboot-project/tree/2.RDBMS)
  : RDBMS를 직접 연결하였고 mybatis를 사용하였습니다.

- [3.JWT](https://github.com/reolinodev/back-springboot-project/tree/3.JWT)
  : spring-security를 사용하여 token을 구현하였습니다. 

- [4.ADMIN-JWT](https://github.com/reolinodev/back-springboot-project/tree/4.ADMIN-JWT)
  : 위의 요소들을 종합해서 간단한 웹 포탈의 백엔드 api를 구현하였습니다.
  
- [ADMIN-JPA](https://github.com/reolinodev/back-jpa-project/tree/2.ADMIN)
  : 4.ADMIN-JWT를 JPA로 변환하였습니다. Gradle을 사용하였기 때문에 JPA 버전을 사용하시려면 해당 프로젝트를 받아서 사용하시면 됩니다.  
