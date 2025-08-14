package com.spring.restfulApiLearning.repository;

import com.spring.restfulApiLearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
