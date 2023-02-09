package com.back.admin.repository;

import com.back.admin.domain.AuthEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository {

    int countByAuthCd(AuthEntity authEntity);

    List<AuthEntity> findAll(AuthEntity authEntity);

    int countAll(AuthEntity authEntity);

    int save(AuthEntity authEntity);

    AuthEntity findByAuthId(String authId);

    int updateAuth(AuthEntity authEntity);

    int deleteByAuthId(String authId);
}
