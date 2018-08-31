package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alex_hu
 * @date 18-8-31 下午2:59
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public int update(int id, User user) {
        return userDao.update(id, user);
    }

    @Override
    public int delete(int id) {
        return userDao.delete(id);
    }
}
