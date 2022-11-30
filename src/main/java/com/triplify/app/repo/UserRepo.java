package com.triplify.app.repo;

import com.triplify.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findbyFirstName(String first_name);
}
