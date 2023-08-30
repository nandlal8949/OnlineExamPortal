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
import org.springframework.web.bind.annotation.RequestParam;

import com.csmtech.entity.Candidate;
import com.csmtech.entity.Role;
import com.csmtech.entity.Users;
import com.csmtech.service.CandidateService;
import com.csmtech.service.RoleService;
import com.csmtech.service.UserService;

@Controller
public class UsersCommonController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CandidateService candidateService;
	
	@GetMapping("/userprofile/{userId}/{roleId}")
	public String getUserProfile(@PathVariable Integer userId, @PathVariable Integer roleId, Model model) {
		if(roleId > 0 && roleId < 4 ) {
			HashMap<String,Object> hashMap = new HashMap<String,Object>();
			List<Role> roleList = roleService.getAllRoles();
			Users user = userService.updateUsersList(userId);
			
			hashMap.put("ROLES", roleList);
			hashMap.put("USER", user);
			model.addAllAttributes(hashMap);
		}
		
		return "userprofile";
	}
	
	@PostMapping("/userprofile")
	public String postUserProfile(@ModelAttribute Users user) {
		if(user.getUserId() != null) {
			user.setUserId(user.getUserId());
		}
		userService.postUsersList(user);
		
		return "redirect:/userprofile/"+user.getUserId()+"/"+user.getRole().getRoleId();
	}
	
	
	@GetMapping("/resetpassword/{userId}/{roleId}")
	public String getResetPassword(@PathVariable Integer userId, @PathVariable Integer roleId, Model model) {
		if(roleId > 0 && roleId < 4 ) {
			HashMap<String,Object> hashMap = new HashMap<String,Object>();
			Users user = userService.updateUsersList(userId);
			
			hashMap.put("USER", user);
			model.addAllAttributes(hashMap);
		}else if(roleId == 0) {
			HashMap<String,Object> hashMap = new HashMap<String,Object>();
			Candidate candId = candidateService.getCandidateInfo(userId);
			
			hashMap.put("CANDIDATE", candId);
			model.addAllAttributes(hashMap);
			return "candidate/candresetpassword";
		}
		return "/resetpassword";
	}
	
	@PostMapping("/resetpassword")
	public String postResetPassword(@RequestParam String oldpassword, @RequestParam String newpassword, @RequestParam String userName) {
		try {
			Users us1 = userService.getUserDetails(userName, oldpassword);
			us1.setPassword(newpassword);
			us1 = userService.postUsersList(us1);
		} catch (Exception e) {
			return "/resetpassword";
		}
		return "/login";
	}
}
