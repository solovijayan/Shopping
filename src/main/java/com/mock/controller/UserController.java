package com.mock.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.mock.model.User;
import com.mock.service.UserService;

/**
 * @author kumaran_m
 *
 */
@Controller
public class UserController {
	
	@Autowired UserService userService;
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}


	@GetMapping(value = "/loginError")
	public String loginError(ModelMap model) {
		System.out.println("Entered failure authenticatin url");
		model.addAttribute("error", "Your username or password is invalid.");
		return "login";

	}

	@GetMapping(value = "/logout")
	public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Entered logout");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		model.addAttribute("message", "You have successfully logged off from application !");
		return "login";

	}

	@GetMapping("/home")
	public String HomePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String role= auth.getAuthorities().toString();
	    model.addAttribute("role",role);
		return "home";

	}

	@GetMapping("/registration")
	public String registrationForm(Model model) {

		model.addAttribute("user", new User());
		return "registration";

	}
	
	@PostMapping(value = "/registration")
	public String addCustomer(@ModelAttribute("user") @Validated User user, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {

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
