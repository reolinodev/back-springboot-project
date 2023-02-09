package com.back.repository;

import com.back.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User findById(String id);

    int save(User user);

    int deleteById(String id);

    List<User> findAll(User user);

    int update(User user);
}
