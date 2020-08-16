package com.bigbird.vuebackstage.controller;

import com.bigbird.vuebackstage.model.User;
import com.bigbird.vuebackstage.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("findOne")
    public User findOne(String id) {
        return userService.findUserById(id);
    }

    @GetMapping("delete")
    public Map<String, Object> deleteOne(String id) {
        Map<String, Object> res = new HashMap<>();
        try {
            userService.deleteUserById(id);
            res.put("success", true);
            res.put("retMsg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("retMsg", "删除失败");
        }

        return res;
    }

    @GetMapping("findAll")
    public Map<String, Object> findAll() {
        Map<String, Object> res = new HashMap<>();
        List<User> results = userService.findAll();
        res.put("total", results.size());
        res.put("results", results);
        return res;
    }

    @PostMapping("update")
    public Map<String, Object> updateUser(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        try {
            userService.updateUser(user);
            res.put("success", true);
            res.put("retMsg", "编辑用户信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("retMsg", "编辑用户信息失败");
        }

        return res;
    }

    @PostMapping("add")
    public Map<String, Object> saveUser(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        try {
            userService.saveUser(user);
            res.put("success", true);
            res.put("retMsg", "添加用户信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("retMsg", "添加用户信息失败");
        }
        return res;
    }

    @PostMapping("addOrUpdate")
    public Map<String, Object> addOrUpdate(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        try {
            if (StringUtils.isEmpty(user.getId())) {
                userService.saveUser(user);
                res.put("retMsg", "添加用户信息成功");
            } else {
                userService.updateUser(user);
                res.put("retMsg", "修改用户信息成功");
            }
            res.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("retMsg", "保存用户信息失败");
        }
        return res;
    }

    @GetMapping("findAllByPage")
    public Map<String, Object> findAllByPage(Integer pageNo, Integer pageSize) {
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 5;
        }
        Map<String, Object> res = new HashMap<>();
        List<User> results = userService.findByPage(pageNo, pageSize);
        res.put("total", userService.getTotalSize());
        res.put("users", results);
        return res;
    }
}
