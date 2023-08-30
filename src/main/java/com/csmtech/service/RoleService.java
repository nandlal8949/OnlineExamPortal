package com.csmtech.service;

import java.util.List;

import com.csmtech.entity.Role;

public interface RoleService {

	List<Role> getAllRoles();

	Role postRoleName(Role role);

	void deleteRoleName(Integer roleId);

	Role updateRoleName(Integer roleId);

}
