package com.back.admin.repository;

import com.back.admin.domain.UserEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    int countByLoginId(String loginId);

    UserEntity findByUserId(String userId);

    int update(UserEntity userEntity);

    List<UserEntity> findAll(UserEntity userEntity);

    int countByAll(UserEntity userEntity);

    int save(UserEntity userEntity);

    int deleteByUserid(String userId);

}
