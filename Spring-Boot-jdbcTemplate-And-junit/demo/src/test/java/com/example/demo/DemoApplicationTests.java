package com.example.demo;

import com.example.demo.entity.User;
import net.minidev.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	/*
	restTemplate refs: https://blog.csdn.net/u012702547/article/details/77917939
	 */

	private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);
	private static final String BASE_URL = "http://127.0.0.1:8080/user";

	private RestTemplate restTemplate;

	@Before
	public void before() {
		restTemplate = new RestTemplate();
		log.info("junit test before ==================================");
	}

	@Test
	public void getUserById() {
		User user = restTemplate.getForObject(BASE_URL + "/1", User.class);
		log.info("user: " + user);
	}

	@Test
	public void add() {
		//post https://blog.csdn.net/ldy1016/article/details/80002126

		User user = new User("Tom", 28);
		ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(BASE_URL, user, Integer.class);
		log.info("add user: " + responseEntity.getBody());
	}

	@Test
	public void userList() {
		List<User> list = restTemplate.getForObject(BASE_URL + "/list", List.class);
		log.info("user list: " + list);
	}

	@Test
	public void update() {
		User user = new User();
		user.setName("alex-alex");
		user.setAge(100000);
		restTemplate.put(BASE_URL + "/{1}", user, 1);
	}

	@Test
	public void delete() {
		restTemplate.delete(BASE_URL + "/{1}", 1);
	}

	@After
	public void after() {
		log.info("junit test after ==================================");
	}

}
