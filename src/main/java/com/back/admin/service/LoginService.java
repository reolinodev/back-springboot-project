package com.back.admin.service;

import com.back.admin.domain.LoginEntity;
import com.back.admin.repository.LoginRepository;
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

    public LoginEntity getLoginId(String loginId) {
        return loginRepository.findByLoginId(loginId);
    }

    public int updateLastLoginDt(LoginEntity loginEntity) {
        return  loginRepository.saveLastLoginDt(loginEntity);
    }

    public int updatePwfailCnt(LoginEntity loginEntity) {
        return loginRepository.savePwfailCnt(loginEntity);
    }

    public int saveToken(LoginEntity loginEntity) {
        return loginRepository.saveToken(loginEntity);
    }

    public LoginEntity getTokenInfo(LoginEntity loginEntity) {
        return loginRepository.findTokenByAccessToken(loginEntity);
    }

    public int saveLoginHistory(LoginEntity loginEntity) {
        return loginRepository.saveLoginHistory(loginEntity);
    }

}
