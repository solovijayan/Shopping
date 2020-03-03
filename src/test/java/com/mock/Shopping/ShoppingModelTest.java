package com.mock.Shopping;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mock.model.User;
import com.mock.repository.UserRepository;
import com.mock.service.UserService;

/**
 * @author sabarinathan.r
 *
 */
//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan(basePackages = "com.mock")
//@ContextConfiguration(classes = { TestBeanConfig.class })
public class ShoppingModelTest {

	private static final Logger logger = LogManager.getLogger(ShoppingModelTest.class);

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	private User user;

	@Before
	public void setup() {
		System.out.println(" Before method..");
		user = new User();
	}

	@Test
	public void testUser() {
		logger.info("Starting test of user model");
		user.setUserId(1);
		user.setUserName("sabari");
		user.setUserPassword("admin");
		assertEquals(1, user.getUserId());
		assertEquals("admin", user.getUserPassword());
		assertEquals("sabari", user.getUserName());
		logger.info("Ending test of user model");
	}
}
