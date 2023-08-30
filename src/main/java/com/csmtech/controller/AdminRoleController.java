package com.csmtech.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csmtech.entity.Role;
import com.csmtech.service.RoleService;
import com.csmtech.service.UserService;

@Controller
public class AdminRoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("admin/rolelist")
	public String getAllRoles(Model model) {
		List<Role> roleList = roleService.getAllRoles();
		model.addAttribute("ROLES", roleList);
		return "admin/rolelist";
	}
	
	@PostMapping("admin/roleform")
	public String postRoleName(@ModelAttribute Role role, Model model) {
		
		
		//check roleName Already Exists
		int size = roleService.getAllRoles().stream().filter(t->t.getRoleName().equalsIgnoreCase(role.getRoleName())).collect(Collectors.toList()).size();
		if(size == 0 && role.getRoleId() == null) {
			//save
			roleService.postRoleName(role);
			model.addAttribute("SAVEROLE", role.getRoleName());
			List<Role> roleList = roleService.getAllRoles();
			model.addAttribute("ROLES", roleList);
			return "admin/rolelist";
		}
		
		if(size == 0 && role.getRoleId() != null) {
			//update 
			role.setRoleId(role.getRoleId());
			roleService.postRoleName(role);
			model.addAttribute("UPDATEROLE", role.getRoleName());
			List<Role> roleList = roleService.getAllRoles();
			model.addAttribute("ROLES", roleList);
			return "admin/rolelist";
		}
		
		if(size > 0 && role.getRoleId() == null) {
			model.addAttribute("BUTTONROLE", "Add");
			return "admin/roleform";
		}
		
		if(size > 0 && role.getRoleId() != null) {
			return "redirect:/admin/roleform/update/"+role.getRoleId();
		}

		return "admin/rolelist";
	}
	
	@GetMapping("admin/roleform")
	public String getRoleForm(Model model) {
		model.addAttribute("BUTTONROLE", "Add");
		return "admin/roleform";
	}
	
	@GetMapping("admin/role/delete/{roleId}")
	public void deleteRoleName(@PathVariable Integer roleId, HttpServletResponse resp) throws IOException {
		System.out.println("runnnnnnnnnnnnnn");
		List<Integer> collect = userService.getUsersList().stream().filter(t->t.getRole().getRoleId() == roleId).map(t->t.getUserId()).collect(Collectors.toList());
		if(collect.size() > 0) {
			collect.forEach(t->{
				userService.deleteUsersList(t);
			});			
		}else {			
			roleService.deleteRoleName(roleId);
		}
		
//		return "redirect:/admin/rolelist";
	}
	
	@GetMapping("admin/roleform/update/{roleId}")
	public String updateRoleName(@PathVariable Integer roleId, Model model) {
		Role role =  roleService.updateRoleName(roleId);
		model.addAttribute("ROLE",role);
		model.addAttribute("BUTTONNAME", "UPDATE ROLE");
		return "admin/roleform";
	}
	
	@PostMapping("admin/roleform/checkroleName")
	public void checkRoleName(@RequestParam String roleName, HttpServletResponse resp) throws IOException {
		int size = roleService.getAllRoles().stream().filter(t->t.getRoleName().equalsIgnoreCase(roleName)).collect(Collectors.toList()).size();
		System.out.println(roleName);
		resp.setContentType("text/html");
		if(size == 0) {
			resp.getWriter().print("success");
		}else {
			resp.getWriter().print("failed");
		}
	}
}
