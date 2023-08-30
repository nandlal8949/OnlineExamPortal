package com.csmtech.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	private String name;
	
	private String userName;
	
	private String password;
	
	private String mobileNo;
	
	private String gender;
	
	private String email;
	
	private String status;
	
	private String userAddress;
	
	private String isDelete;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Role.class)
	@JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "user_role_id_foreign_key") )
	private Role role;

}
