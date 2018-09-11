package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author alex_hu
 * @date 18-9-10 下午1:30
 */
@Mapper
public interface UserMapper {

    @Select("select * from t_user where name = #{username}")
    List<User> findByUsername(@Param("username") String username);

    int insert(User user);

}
