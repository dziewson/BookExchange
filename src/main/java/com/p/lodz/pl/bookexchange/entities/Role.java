package com.p.lodz.pl.bookexchange.entities;

import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
@Access(AccessType.FIELD)
public class Role implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2877552886231583952L;
	private Long id;
	private String userRole;
	private Set<UserData> users;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Access(AccessType.PROPERTY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return userRole;
	}

	public void setRole(String role) {
		this.userRole = role;
	}

	@ManyToMany(mappedBy = "roles", cascade = {CascadeType.ALL})
	@Access(AccessType.PROPERTY)
	public Set<UserData> getUsers() {
		return users;
	}

	public void setUsers(Set<UserData> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return userRole;
	}
	@Override
	public String toString() {
		return userRole;
	}
}