package com.back.repository;

import com.back.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserRepository {

    Logger logger = Logger.getLogger(UserRepository.class.getSimpleName());

    public User findById(Integer id) {
        logger.info("findById실행");

        User data = new User();
        data.setId(1);
        data.setEmail("aaa@gmail.com");
        data.setName("AAA");
        data.setPassword("12345");
        data.setPhone("01011112222");
        data.setBirth("19000101");
        return data;
    }

    public int save(User user) {
        logger.info("save실행");

        return 1;
    }

    public int deleteById(Integer id) {
        logger.info("deleteById실행");

        return 1;
    }

    public List<User> findAll(User user) {
        logger.info("findAll실행");

        List<User> list = new ArrayList<>();
        User data = new User();
        data.setId(1);
        data.setEmail("aaa@gmail.com");
        data.setName("AAA");
        data.setPassword("12345");
        data.setPhone("01011112222");
        data.setBirth("19000101");
        list.add(data);

        User data2 = new User();
        data2.setId(2);
        data2.setEmail("bbb@gmail.com");
        data2.setName("BBB");
        data2.setPassword("67890");
        data2.setPhone("01033334444");
        data2.setBirth("20001231");
        list.add(data2);

        return list;
    }

    public int update(User user) {
        logger.info("update실행");

        return 1;
    }
}

