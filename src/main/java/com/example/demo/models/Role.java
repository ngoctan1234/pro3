package com.example.demo.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="role")
public class Role {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(mappedBy="role")
	private Set<User_Role> userRoles;
	public Role() {
		
	}
	public Role(Long id, String name, Set<User_Role> userRoles) {
		super();
		this.id = id;
		this.name = name;
		this.userRoles = userRoles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User_Role> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<User_Role> userRoles) {
		this.userRoles = userRoles;
	}
	
	
}
