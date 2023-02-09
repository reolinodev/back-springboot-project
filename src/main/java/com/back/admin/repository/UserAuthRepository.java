package com.back.admin.repository;

import com.back.admin.domain.UserAuthEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository {

    List<UserAuthEntity> findAll(UserAuthEntity userAuthEntity);

    int countAll(UserAuthEntity userAuthEntity);

    List<UserAuthEntity> findAllInputUser(UserAuthEntity userAuthEntity);

    int countByInputUser(UserAuthEntity userAuthEntity);

    int save(UserAuthEntity userAuthEntity);

    int deleteUserAuth(UserAuthEntity userAuthEntity);
}
