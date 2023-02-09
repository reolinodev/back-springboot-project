package com.back.admin.service;

import com.back.admin.domain.AuthEntity;
import com.back.admin.repository.AuthRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authARepository;

    public int checkAuthCd(AuthEntity authEntity) {
        return authARepository.countByAuthCd(authEntity);
    }

    public int saveAuth(AuthEntity authEntity) {
        return authARepository.save(authEntity);
    }

    public List<AuthEntity> getAuthList(AuthEntity authEntity) {
        authEntity.setStart_idx(authEntity.getPage_per(), authEntity.getCurrent_page());
        return authARepository.findAll(authEntity);
    }

    public int getAuthCount(AuthEntity authEntity) {
        return authARepository.countAll(authEntity);
    }

    public AuthEntity getAuthData(String authId) {
        return authARepository.findByAuthId(authId);
    }

    public int updateAuth(AuthEntity authEntity){
        return authARepository.updateAuth(authEntity);
    }

    public int deleteAuth(String authId) throws Exception {
        return authARepository.deleteByAuthId(authId);
    }
}
