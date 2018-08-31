package com.example.demo;

import com.example.demo.entity.MyProperty1;
import com.example.demo.entity.MyProperty2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
	private MyProperty1 myProperty1;

	@Autowired
	private MyProperty2 myProperty2;

	@Test
	public void getMyProperty1() {
		logger.info(myProperty1.toString());
	}

	@Test
	public void getMyProperty2() {
		logger.info(myProperty2.toString());
	}

}
