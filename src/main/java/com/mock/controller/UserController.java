package com.mock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mock.model.Item;
import com.mock.model.User;
import com.mock.service.ItemService;
import com.mock.service.UserService;

/**
 * @author kumaran_m
 * 
 *         This class is for managing User,Manager Login & Registration
 *
 */
@Controller
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	ItemService itemService;

	/**
	 * @param model
	 * @return login page
	 */
	@GetMapping("/login")
	public String login(Model model) {
		logger.info("In login");
		return "login";
	}

	/**
	 * @param model
	 * @return login page with error message
	 */
	@GetMapping(value = "/loginError")
	public String loginError(ModelMap model) {
		logger.info("Entered failure authenticatin url");
		model.addAttribute("error", "Your username or password is invalid.");
		return "login";
	}

	/**
	 * @param model
	 * @param request
	 * @param response
	 * @return login page with logout message
	 */
	@GetMapping(value = "/logout")
	public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Entered logout");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		model.addAttribute("message", "You have successfully logged off from application !");
		return "login";

	}

	/**
	 * @param model
	 * @return home page with role
	 */
	@GetMapping("/home")
	public ModelAndView HomePage(Model model) {
		logger.info("In home page");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();
		logger.info(role);
		List<Item> itemList = itemService.getAllItems();
		if (role.equals("[user]")) {
			return new ModelAndView("userhome", "itemList", itemList);
		} else {
			return new ModelAndView("adminhome", "itemList", itemList);
		}
	}

	/**
	 * @param model
	 * @return registration page
	 */
	@GetMapping("/registration")
	public String registrationForm(Model model) {
		logger.info("In Registration page");
		model.addAttribute("user", new User());
		return "registration";

	}

	/**
	 * @param user
	 * @param bindingResult
	 * @param model
	 * @return login page after registration
	 */
	@PostMapping(value = "/registration")
	public String addCustomer(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model) {
		logger.info("In Registration save");
		if (bindingResult.hasErrors()) {
			logger.info("In Registration save error occured");
			return "registration";
		} else {
			try {
				userService.saveRegistration(user);
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
				user.setUserPassword(null);
				return "registration";
			}
		}
		model.addAttribute("message", "User Details Saved Successfully");
		model.addAttribute("user", new User());
		return "login";
	}
}
