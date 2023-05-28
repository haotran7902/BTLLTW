package com.samsung.ltw.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
	private Integer user_id;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinTable(name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public String printRoles(Set<Role> set) {
		String s = "";
		for(Role r: set) {
			s += r.getRole_id() + " " + r.getName() + " ";
		}
		return s;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", username="
				+ username + ", password=" + password + ", email=" + email + ", roles=" + printRoles(roles) + "]";
	}
}
