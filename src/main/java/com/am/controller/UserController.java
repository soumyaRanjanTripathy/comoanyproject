package com.am.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.am.entity.User;
import com.am.service.UserService;
import com.am.util.EmailUtil;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private EmailUtil emailUtil;
	
	@GetMapping("/")
	public String user(Model m ) {
		return "login";
		
	}
	@GetMapping("/register")
	public String userRegister(Model m ) {
		return "register";
		
	}
	@PostMapping("/createUser")
	public String saveUser(@ModelAttribute User user,RedirectAttributes redirAttrs) {
		
		boolean isEmailExist=userService.checkEmail(user.getEmail());
		if(isEmailExist) {
			 redirAttrs.addFlashAttribute("message", "Email Already Exist");
		}else {
			User user1=userService.createUser(user);
			if(user1!=null){
				 emailUtil.sendEmail(user.getEmail(), "Welcome TO NIC", "Hello :"+user.getName());
				 redirAttrs.addFlashAttribute("message", "Register Successfully");
			}else {
				 redirAttrs.addFlashAttribute("message", "Something wrong on server");
			}
		}
		return "redirect:/register";
	}
	@PostMapping("/login")	
	public ResponseEntity<Object> userLogin(@ModelAttribute User user,HttpSession session) {		
		int status=userService.checkUserExist(user.getEmail(), user.getPassword());
		if(status!=0) {
			session.setAttribute("msg", "Welcome "+user.getEmail());
		}	
		return new ResponseEntity<Object>(status, HttpStatus.OK);
	}
	@GetMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
}
