package com.placementhub.placementhub.repository;

import com.placementhub.placementhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
}