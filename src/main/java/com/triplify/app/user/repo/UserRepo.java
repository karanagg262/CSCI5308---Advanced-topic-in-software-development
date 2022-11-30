package com.triplify.app.user.repo;

import com.triplify.app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findbyFirstName(String first_name);
}
