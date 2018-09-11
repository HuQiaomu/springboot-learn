package com.example.demo.mapper;

import com.example.demo.entity.User2;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @author alex_hu
 * @date 18-9-10 下午5:06
 */
@Mapper
public interface UserMapper2 extends BaseMapper<User2> {
}
