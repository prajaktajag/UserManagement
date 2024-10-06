
package com.itwebsmart.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwebsmart.app.entities.User;
import com.itwebsmart.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	 @Autowired
	    private UserService userService;

	 @PostMapping("/getall")
	    public Page<User> getUsers(@RequestParam int page, @RequestParam int size) {
	        return userService.getAllUsers(PageRequest.of(page, size));
	    }
}
