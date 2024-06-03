package com.altun.conferencemanagementsystem.service.impl;

import com.altun.conferencemanagementsystem.entity.Role;
import com.altun.conferencemanagementsystem.enums.RoleName;
import com.altun.conferencemanagementsystem.repository.RoleRepository;
import com.altun.conferencemanagementsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;
    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
