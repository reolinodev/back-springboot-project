package com.back.service;

import com.back.domain.LoginEntity;
import com.back.repository.LoginRepository;
import com.back.support.CryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public int checkLoginId(LoginEntity loginEntity) {
        return loginRepository.countByLoginId(loginEntity);
    }

    public int checkUserPw(LoginEntity loginEntity) throws Exception {
        loginEntity.user_pw = CryptUtils.encryptSha256(loginEntity.user_pw);
        return loginRepository.countByLoginIdAndUserPw(loginEntity);
    }

    public LoginEntity getLoginId(LoginEntity loginEntity) {
        return loginRepository.findByLoginId(loginEntity);
    }

    public int updateLastLoginDt(LoginEntity loginEntity) {
        return  loginRepository.saveLastLoginAt(loginEntity);
    }

    public int updatePwfailCnt(LoginEntity loginEntity) {
        return loginRepository.savePwfailCnt(loginEntity);
    }

    public int inputToken(LoginEntity loginEntity) {
        return loginRepository.saveToken(loginEntity);
    }

    public LoginEntity getTokenInfo(LoginEntity loginEntity) {
        return loginRepository.findTokenByAccessToken(loginEntity);
    }
}
