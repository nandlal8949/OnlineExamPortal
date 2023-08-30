package com.csmtech.service;

import java.util.List;

import com.csmtech.entity.Users;

public interface UserService {

	Users getUserDetails(String userName, String password);

	List<Users> getUsersList();

	Users postUsersList(Users users);

	void deleteUsersList(Integer userId);

	Users updateUsersList(Integer userId);

}
