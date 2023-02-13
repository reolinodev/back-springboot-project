CREATE SEQUENCE SEQ_USER START 1;

CREATE TABLE TB_USER (
     USER_ID VARCHAR(10) NOT NULL,
     LOGIN_ID VARCHAR(50) NOT NULL,
     USER_NM VARCHAR(50) NULL,
     USER_PW VARCHAR(70) NULL,
     TEL_NO VARCHAR(20) NULL,
     USE_YN CHAR(1) NULL DEFAULT 'Y',
     PW_FAIL_CNT INT4 NULL,
     CREATED_AT TIMESTAMP NULL,
     UPDATED_AT TIMESTAMP NULL,
     CREATED_ID VARCHAR(10) NOT NULL,
     UPDATED_ID VARCHAR(10) NOT NULL,
     LAST_LOGIN_AT TIMESTAMP NULL,
     CONSTRAINT TB_USER_PK PRIMARY KEY (USER_ID)
);


CREATE TABLE TB_LOGIN (
    ACCESS_TOKEN VARCHAR(500) NULL,
    REFRESH_TOKEN VARCHAR(500) NULL,
    UPDATED_AT TIMESTAMP NULL,
    LOGIN_ID VARCHAR(50) NULL
);

