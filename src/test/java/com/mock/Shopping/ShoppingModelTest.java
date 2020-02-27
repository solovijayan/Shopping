package com.mock.Shopping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mock.model.User;
import com.mock.service.UserService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.mock")
@ContextConfiguration(classes = { TestBeanConfig.class })
public class ShoppingModelTest {
	
	private static final Logger logger = LogManager.getLogger(ShoppingModelTest.class);
	
	@Autowired
	UserService userService;
	private User user;

	@Before
	public void setup() {
		System.out.println(" Before method..");
		user = new User();
	}

	@Test
	public void testUser() {
		logger.info("Starting test of user model");
		
		user.setUserName("admin");
		user.setUserPassword("admin");
		user.setUserRole("admin");
		/*userService.saveUser(user);
		List<User> user = userService.FetchPerson();
		for (User user1: user) {
			assertEquals("admin", user1.getUserName());
		}*/
		logger.info("Ending test of user model");
	}
}
