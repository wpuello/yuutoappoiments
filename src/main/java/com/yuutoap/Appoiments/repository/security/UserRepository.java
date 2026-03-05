package com.yuutoap.Appoiments.repository.security;


import com.yuutoap.Appoiments.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
