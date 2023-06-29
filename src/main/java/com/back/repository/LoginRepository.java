package com.back.repository;

import com.back.domain.LoginEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository {

    int countByLoginId(LoginEntity loginEntity);

    int countByLoginIdAndUserPw(LoginEntity loginEntity);

    LoginEntity findByLoginId(LoginEntity loginEntity);

    int saveLastLoginAt(LoginEntity loginEntity);

    int savePwfailCnt(LoginEntity loginEntity);

    int saveToken(LoginEntity loginEntity);

    LoginEntity findTokenByAccessToken(LoginEntity loginEntity);

}
