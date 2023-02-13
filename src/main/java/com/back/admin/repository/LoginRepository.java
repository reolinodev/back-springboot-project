package com.back.admin.repository;

import com.back.admin.domain.LoginEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository {

    int countByLoginId(LoginEntity loginEntity);

    int countByLoginIdAndUserPw(LoginEntity loginEntity);

    LoginEntity findByLoginId(String loginId);

    int saveLastLoginAt(LoginEntity loginEntity);

    int savePwfailCnt(LoginEntity loginEntity);

    int saveToken(LoginEntity loginEntity);

    LoginEntity findTokenByAccessToken(LoginEntity loginEntity);

    int saveLoginHistory(LoginEntity loginEntity);

}
