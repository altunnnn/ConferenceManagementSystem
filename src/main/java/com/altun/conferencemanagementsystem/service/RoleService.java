package com.altun.conferencemanagementsystem.service;

import com.altun.conferencemanagementsystem.entity.Role;
import com.altun.conferencemanagementsystem.enums.RoleName;

import java.util.Optional;

public interface RoleService {
    Role saveRole(Role role);

    Optional<Role> findByName(RoleName roleName);
}
