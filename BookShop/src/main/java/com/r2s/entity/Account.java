package com.r2s.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;

import com.r2s.entity.User;

import com.r2s.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Setter
@Getter
@Table(name = "account")
public class Account implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer accountId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "createdDate")
	private Date createdDate;

	@Column(name ="user_id")
	private Integer userId;

	@Column(name = "role_id")
	private Integer roleId;
	
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@OneToOne
	private User user;

	@OneToMany
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private Set<Role> roles;

}