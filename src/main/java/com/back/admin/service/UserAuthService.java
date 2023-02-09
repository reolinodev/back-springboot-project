package com.back.admin.service;

import com.back.admin.domain.UserAuthEntity;
import com.back.admin.repository.UserAuthRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserAuthRepository userAuthRepository;

    public List<UserAuthEntity> getUserAuthList(UserAuthEntity userAuthEntity) {
        userAuthEntity.setStart_idx(userAuthEntity.getPage_per(), userAuthEntity.getCurrent_page());
        return userAuthRepository.findAll(userAuthEntity);
    }

    public int getUserAuthCount(UserAuthEntity userAuthEntity) {
        return userAuthRepository.countAll(userAuthEntity);
    }

    public List<UserAuthEntity> getInputUserAuthList(UserAuthEntity userAuthEntity) {
        userAuthEntity.setStart_idx(userAuthEntity.getPage_per(), userAuthEntity.getCurrent_page());
        return userAuthRepository.findAllInputUser(userAuthEntity);
    }

    public int getInputUserAuthCount(UserAuthEntity userAuthEntity) {
        return userAuthRepository.countByInputUser(userAuthEntity);
    }

    public int saveUserAuth(UserAuthEntity userAuthEntity) {
        int result = 0;
        String[] arr = userAuthEntity.user_arr;
        for (String j : arr) {
            userAuthEntity.user_id = j;
            result = userAuthRepository.save(userAuthEntity);
        }

        return result;
    }

    public int deleteUserAuth(UserAuthEntity userAuthEntity) throws Exception {
        int tot_result = 1;
        String[] arr = userAuthEntity.user_arr;
        for (String j : arr) {
            int sub_result= 0;
            userAuthEntity.user_id = j;
            sub_result = userAuthRepository.deleteUserAuth(userAuthEntity);
            if (sub_result !=1) tot_result = 0;
        }

        return tot_result;
    }
}
