package com.bigbird.vuebackstage.service;

import com.bigbird.vuebackstage.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(String id);

    User findUserById(String id);

    List<User> findAll();

    List<User> findByPage(Integer pageNo, Integer pageSize);

    int getTotalSize();
}
