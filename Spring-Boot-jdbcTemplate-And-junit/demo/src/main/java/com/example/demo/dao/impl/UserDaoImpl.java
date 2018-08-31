package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author alex_hu
 * @date 18-8-31 下午2:42
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(Integer id) {
        List<User> list = jdbcTemplate.query("select * from t_user where id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(User.class));
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<User> getUserList() {
        List<User> list = jdbcTemplate.query("select * from t_user ", new Object[]{},
                new BeanPropertyRowMapper<>(User.class));
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    @Override
    public int add(User user) {
        return jdbcTemplate.update("insert into t_user(name, age) values(?, ?)",
                user.getName(), user.getAge());
    }

    @Override
    public int update(int id, User user) {
        return jdbcTemplate.update("update t_user set name = ?, age = ? where id = ?",
                user.getName(), user.getAge(), id);
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("delete from t_user where id = ? ", id);
    }
}
