package com.mock.Shopping;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.mock.controller.ItemController;
import com.mock.controller.UserController;
import com.mock.model.Item;
import com.mock.model.User;
import com.mock.repository.UserRepository;
import com.mock.service.ItemService;
import com.mock.service.ItemServiceImpl;
import com.mock.service.UserService;
import com.mock.service.UserServiceImpl;

/**
 * @author sabarinathan.r
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;
	@InjectMocks
	private ItemController itemController;
	private HttpServletRequest request;
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private BindingResult result;
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	@Mock
	private SecurityContext securityContext;

	@Mock
	private SecurityContextHolderStrategy strategy;
	@Mock
	private Authentication auth;
	@SuppressWarnings("rawtypes")
	@Mock
	private List list;
	private List<Item> list1;
	@Mock
	private UserRepository userRepository;
	@Mock
	private UserService userService;
	@Mock
	private ItemService itemService;

	private User user;
	private Field field3, field1, field2, field4, field5;

	@Mock
	private Model model;
	@Mock
	private ModelMap modelmap;

	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setUserPassword("1234");

		userService = new UserServiceImpl();
		field1 = UserController.class.getDeclaredField("userService");
		field1.setAccessible(true);
		field1.set(userController, userService);

		field2 = UserServiceImpl.class.getDeclaredField("userRepository");
		field2.setAccessible(true);
		field2.set(userService, userRepository);

		field3 = UserServiceImpl.class.getDeclaredField("passwordEncoder");
		field3.setAccessible(true);
		field3.set(userService, passwordEncoder);
	}

	@Test
	public void testShowUserRegisterForm() {
		assertEquals("registration", userController.registrationForm(model));
	}

	@Test
	public void testLogin() {
		assertEquals("login", userController.login(model));
	}

	@Test
	public void testLogout() {

		assertEquals("login", userController.logout(modelmap, request, response));

	}

	@Test
	public void testSaveUserRegistrationForm() {
		assertEquals("login", userController.addCustomer(user, result, model));
	}

	@Test
	public void testSaveUserRegistrationForm_BindingResultHasErrors() {
		Mockito.when(result.hasErrors()).thenReturn(true);
		assertEquals("registration", userController.addCustomer(user, result, model));
	}

	@After
	public void tearDown() throws Exception {
		result = null;
		field3 = null;
		passwordEncoder = null;
		userController = null;
		user = null;
		userRepository = null;
		userService = null;
		field1 = null;
		field2 = null;
		field4 = null;
	}

}
