package com.yuutoap.Appoiments.service.security.contract;

import com.yuutoap.Appoiments.model.security.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    Optional<Role> findById(UUID id);
    Optional<Role> findByName(String name);
    List<Role> findAll();
    Role save(Role role);
    List<Role> saveAll(List<Role> roles);
}
