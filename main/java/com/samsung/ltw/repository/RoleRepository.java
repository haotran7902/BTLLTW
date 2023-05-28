package com.samsung.ltw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsung.ltw.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByName(String name);
}
