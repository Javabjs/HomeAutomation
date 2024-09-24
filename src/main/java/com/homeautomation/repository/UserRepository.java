package com.homeautomation.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeautomation.model.User;



public interface UserRepository extends JpaRepository<User,UUID> {

}
