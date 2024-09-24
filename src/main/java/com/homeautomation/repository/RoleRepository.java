package com.homeautomation.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeautomation.model.Role;



@Repository

public interface RoleRepository  extends JpaRepository<Role, UUID>  {

}
