package com.yuutoap.Appoiments.service.security.contract;

import com.yuutoap.Appoiments.model.security.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    User save(User user);
    List<User> findAll();
}
