package com.example.crud15hours.repository;

import com.example.crud15hours.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
