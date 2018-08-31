package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

/**
 * @author alex_hu
 * @date 18-8-31 下午2:58
 */
public interface UserService {

    User getUserById(Integer id);

    List<User> getUserList();

    int add(User user);

    int update(int id, User user);

    int delete(int id);

}
