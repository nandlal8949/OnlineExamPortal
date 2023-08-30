package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.Role;
import com.csmtech.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}
	@Override
	public Role postRoleName(Role role) {
		return roleRepository.save(role);
	}
	@Override
	public void deleteRoleName(Integer roleId) {
		roleRepository.deleteById(roleId);
	}
	@Override
	public Role updateRoleName(Integer roleId) {
		return roleRepository.findById(roleId).get();
	}

}
