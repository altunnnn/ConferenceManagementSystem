package com.altun.conferencemanagementsystem.service;

import com.altun.conferencemanagementsystem.entity.Role;
import com.altun.conferencemanagementsystem.enums.RoleName;
import com.altun.conferencemanagementsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {


    private final RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
