package com.csmtech.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.csmtech.entity.Role;
import com.csmtech.entity.Users;
import com.csmtech.service.RoleService;
import com.csmtech.service.UserService;

@Controller
public class AdminUsersController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("admin/users")
	public String getUsersList(Model model) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		List<Users> userList = userService.getUsersList();
		List<Role> roleList = roleService.getAllRoles();
		
		hashMap.put("ROLES", roleList);
		hashMap.put("USERSLIST", userList);
		model.addAllAttributes(hashMap);
		return "admin/users";
	}
	
	@PostMapping("admin/users")
	public String postUsersList(@ModelAttribute Users users) {
		if(users.getUserId() != null) {
			users.setUserId(users.getUserId());
		}
		userService.postUsersList(users);
		return "redirect:/admin/users";
	}
	
	@GetMapping("admin/users/delete/{userId}")
	public String deleteUsersList(@PathVariable Integer userId) {
		userService.deleteUsersList(userId);
		return "redirect:/admin/users";
	}
	
	@GetMapping("admin/users/update/{userId}")
	public String updateUsersList(@PathVariable Integer userId, Model model) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		List<Users> userList = userService.getUsersList();
		List<Role> roleList = roleService.getAllRoles();
		Users user = userService.updateUsersList(userId);
		
		hashMap.put("ROLES", roleList);
		hashMap.put("USERSLIST", userList);
		hashMap.put("USER", user);
		model.addAllAttributes(hashMap);
		
		return "admin/users";
	}
}
