package com.example.finalprojectspring.model.repo;

import com.example.finalprojectspring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByLogin(String login);
}