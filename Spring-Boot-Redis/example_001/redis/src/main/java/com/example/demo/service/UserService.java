package com.example.demo.service;

import com.example.demo.entity.User;

/**
 * @author alex_hu
 * @date 18-9-11 下午1:19
 */
public interface UserService {

    User saveOrUpdate(User user);

    User get(Long id);

    void delete(Long id);

}
