package com.csmtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csmtech.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	Users findByUserNameAndPassword(String userName, String password);

}
