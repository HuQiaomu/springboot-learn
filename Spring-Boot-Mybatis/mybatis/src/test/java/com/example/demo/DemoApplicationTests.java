package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.entity.User2;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserMapper2;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserMapper2 userMapper2;

	@Test
	public void test() {
		final int row1 = userMapper.insert(new User("u1", 1));
		log.info("[添加结果] - [{}]", row1);
		final int row2 = userMapper.insert(new User("u2", 2));
		log.info("[添加结果] - [{}]", row2);
		final int row3 = userMapper.insert(new User("u1", 3));
		log.info("[添加结果] - [{}]", row3);
		final List<User> u1 = userMapper.findByUsername("u1");
		log.info("[根据用户名查询] - [{}]", u1);
	}

	@Test
	public void test1() {
		// TODO 模拟分页
		userMapper2.insertSelective(new User2("u1", 1));
		userMapper2.insertSelective(new User2("u1", 1));
		userMapper2.insertSelective(new User2("u1", 1));
		userMapper2.insertSelective(new User2("u1", 1));
		userMapper2.insertSelective(new User2("u1", 1));
		userMapper2.insertSelective(new User2("u1", 1));
		userMapper2.insertSelective(new User2("u1", 1));
		userMapper2.insertSelective(new User2("u1", 1));
		userMapper2.insertSelective(new User2("u1", 1));
		userMapper2.insertSelective(new User2("u1", 1));
		userMapper2.insertSelective(new User2("u1", 1));
		userMapper2.insertSelective(new User2("u1", 1));

		// TODO 分页 + 排序 this.userMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
		final PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> {
			this.userMapper2.selectAll();
		});
		log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());


		PageHelper.startPage(1, 10).setOrderBy("id desc");
		final PageInfo<User2> userPageInfo = new PageInfo<>(this.userMapper2.selectAll());
		log.info("[普通写法] - [{}]", userPageInfo);

	}

}
