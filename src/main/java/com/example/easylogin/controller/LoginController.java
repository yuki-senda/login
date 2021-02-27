package com.example.easylogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.easylogin.model.dao.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepos;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(
		@RequestParam("userName") String userName,
		@RequestParam("password") String password,
		Model m) {
		
		String message = "welcome!";
		
		List<com.example.easylogin.model.entity.User> users = userRepos.findByUserNameAndPassword(userName, password);
		if(users.size() > 0) {
			com.example.easylogin.model.entity.User user = users.get(0);
			message += user.getFullName();
		}else {
			message += "guest";
		}
		m.addAttribute("message",message);
		
		return "login";
	}

}
