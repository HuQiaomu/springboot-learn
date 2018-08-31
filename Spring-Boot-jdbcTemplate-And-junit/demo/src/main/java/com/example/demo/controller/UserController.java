package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author alex_hu
 * @date 18-8-31 下午3:02
 */

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "id") Integer id) {
        log.info("id: " + id);
        return userService.getUserById(id);
    }

    @GetMapping("user/list")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @PostMapping("user")
    public int add(@RequestBody User user) {
        log.info("add user ===== " + user);
        return userService.add(user);
    }

    @DeleteMapping("user/{id}")
    public int delete(@PathVariable(value = "id") int id) {
        return userService.delete(id);
    }

    @PutMapping("user/{id}")
    public int update(@PathVariable(value = "id") int id, @RequestBody User user) {
        log.info("put user: " + id + " " + user);
        return userService.update(id, user);
    }

}
