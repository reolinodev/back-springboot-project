package com.back.service;

import com.back.domain.User;
import com.back.repository.UserRepository;
import com.back.support.CryptUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 사용자를 전체조회 합니다.
     */
    public List<User> findAll(User user) {
        return userRepository.findAll(user);
    }


    /**
     * 사용자를 상세 조회 합니다.
     */
    public User findById(String id) {
        return userRepository.findById(id);
    }


    /**
     * 사용자를 생성합니다.
     */
    public int save(User user) throws Exception {
        user.user_pw = CryptUtils.encryptSha256(user.user_pw);
        return userRepository.save(user);
    }


    /**
     * 사용자 정보를 수정합니다.
     */
    public int update(User user) throws Exception {
        user.user_pw = CryptUtils.encryptSha256(user.user_pw);
        return userRepository.update(user);
    }


    /**
     * 사용자를 삭제 합니다.
     */
    public int deleteById(String id) {
        return userRepository.deleteById(id);
    }
}
