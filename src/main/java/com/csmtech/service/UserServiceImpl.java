package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.Users;
import com.csmtech.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public Users getUserDetails(String userName, String password) {
		return userRepository.findByUserNameAndPassword(userName,password);
	}
	@Override
	public List<Users> getUsersList() {
		return userRepository.findAll();
	}
	@Override
	public Users postUsersList(Users users) {
		
		return userRepository.save(users);
	}
	@Override
	public void deleteUsersList(Integer userId) {
		userRepository.deleteById(userId);
		
	}
	@Override
	public Users updateUsersList(Integer userId) {
		return userRepository.findById(userId).get();
	}

}
