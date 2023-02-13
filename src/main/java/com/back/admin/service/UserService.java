package com.back.admin.service;

import com.back.admin.domain.UserEntity;
import com.back.admin.repository.UserRepository;
import com.back.support.CryptUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public int checkLoginId(String loginId) {
        return userRepository.countByLoginId(loginId);
    }

    public int saveUser(UserEntity userEntity) throws Exception {
        userEntity.user_pw = CryptUtils.encryptSha256(userEntity.user_pw);
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getUserList(UserEntity userEntity) {
        userEntity.setStart_idx(userEntity.page_per, userEntity.current_page);
        return userRepository.findAll(userEntity);
    }

    public int getUserCount(UserEntity userEntity) {
        return userRepository.countByAll(userEntity);
    }

    public UserEntity getUserData(String userId) {
        return userRepository.findByUserId(userId);
    }

    public int updateUser(UserEntity userEntity) throws Exception {
        if(userEntity.user_pw != null){
            userEntity.user_pw = CryptUtils.encryptSha256(userEntity.user_pw);
        }

        return userRepository.update(userEntity);
    }

    public int deleteUser(String userId) throws Exception {
        return userRepository.deleteByUserid(userId);
    }
}
