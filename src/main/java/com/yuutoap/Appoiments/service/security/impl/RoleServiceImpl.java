package com.yuutoap.Appoiments.service.security.impl;

import com.yuutoap.Appoiments.model.security.Role;
import com.yuutoap.Appoiments.repository.security.RoleRepository;
import com.yuutoap.Appoiments.service.security.contract.RoleService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findById(UUID id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> saveAll(List<Role> roles) {
        return roleRepository.saveAll(roles);
    }
}
