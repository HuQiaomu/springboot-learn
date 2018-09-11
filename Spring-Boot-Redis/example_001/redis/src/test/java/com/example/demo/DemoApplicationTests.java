package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Serializable> redisCacheTemplate;

	/*
	redis 终端命令
	进入: redis-cli
	列出所有key: keys *
	获取指定key的value: get key
	清除所有数据: flushall
	 */
	@Test
	public void test() {
		// TODO 测试线程安全
		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		IntStream.range(0, 1000).forEach(i -> {
			// 多线程累加一个数，测试　redis 存储是否是线程安全的
			executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1));
		});
		final String kk = stringRedisTemplate.opsForValue().get("kk");
		log.info("[线程操作结果] - [{}]", kk);

		stringRedisTemplate.opsForValue().set("k1", "v1");
		final String k1 = stringRedisTemplate.opsForValue().get("k1");
		log.info("[字符缓存结果] - [{}]", k1);
		final String k11 = stringRedisTemplate.opsForValue().get("k11");
		log.info("[字符缓存结果不存在的K11] - [{}]", k11);

		// TODO 删除 key
		final Boolean del1 =  stringRedisTemplate.delete("k1");
		log.info("[删除K1结果] - [{}]", del1);

		final Boolean del3 =  stringRedisTemplate.delete("k3");
		log.info("[删除不存在的K3结果] - [{}]", del3);

		// TODO 设置　key 的过期时间
		stringRedisTemplate.opsForValue().set("k2", "v2", 3, TimeUnit.SECONDS);

		// TODO 以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
		String key = "alex:user:1";
		redisCacheTemplate.opsForValue().set(key, new User(1L, "alex", "alex"));
		final User user = (User) redisCacheTemplate.opsForValue().get(key);
		log.info("[对象缓存结果] - [{}]", user);

		// TODO 删除所有 key
		final Set<String> allKeys = redisCacheTemplate.keys("*");
		log.info("[所有key({})] - [{}]", allKeys.size(), Arrays.toString(allKeys.toArray()));
		Long flushall = redisCacheTemplate.delete(allKeys);
		log.info("[删除 key 数量] - [{}]", flushall);

		//判断 key 是否存在
//		redisCacheTemplate.hasKey("k1")
	}

	@Autowired
	private UserService userService;

	@Test
	public void test1() {
		//　前置条件是这些缓存都是用 id 做为 key 的

		// 会将操作结果放在缓存
		final User user = userService.saveOrUpdate(new User(5L, "u5","p5"));
		log.info("[saveOrUpdate] - [{}]", user);
		// 获取　id = 5 数据时，则是从缓存中获取的，因为此时缓存已经有该条数据了
		final User user1 = userService.get(5L);
		log.info("[get] - [{}]", user1);
		// 删除　id = 5 数据时，对应的也会清除缓存中 id = 5 的数据
//		userService.delete(5L);
	}


}
