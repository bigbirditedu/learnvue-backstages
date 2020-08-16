package com.bigbird.vuebackstage.service;

import com.bigbird.vuebackstage.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    /**
     * 模拟数据库存储<uid,user>
     */
    public static Map<String, User> users = new HashMap<>();

    @Override
    public void saveUser(User user) {
        String uid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        user.setId(uid);
        users.put(uid, user);
    }

    @Override
    public void updateUser(User user) {
        User user1 = users.get(user.getId());
        if (user1 != null) {
            users.put(user.getId(), user);
        } else {
            throw new RuntimeException("用户不存在");
        }
    }

    @Override
    public void deleteUserById(String id) {
        users.remove(id);
    }

    @Override
    public User findUserById(String id) {
        return users.get(id);
    }

    @Override
    public List<User> findAll() {
        Collection<User> values = users.values();
        List<User> users = values.stream().collect(Collectors.toList());
        return users;
    }

    public List<User> findByPage(Integer pageNo, Integer pageSize) {
        Collection<User> values = users.values();
        List<User> users = values.stream().collect(Collectors.toList());
        int start = (pageNo - 1) * pageSize;
        int end = start + pageSize;
        if (end > users.size()) {
            end = users.size();
        }

        List<User> usersList = users.subList(start, end);
        return usersList;
    }

    @Override
    public int getTotalSize() {
        return users.values().size();
    }
}
