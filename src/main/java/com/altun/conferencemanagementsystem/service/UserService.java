package com.altun.conferencemanagementsystem.service;

import com.altun.conferencemanagementsystem.entity.Role;
import com.altun.conferencemanagementsystem.entity.User;
import com.altun.conferencemanagementsystem.enums.RoleName;
import com.altun.conferencemanagementsystem.repository.RoleRepository;
import com.altun.conferencemanagementsystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    //TODO:PasswordEncoder implementation

    public User saveUser(User user) {
        user.setPassword(user.getPassword());//todo: passwordEncoder.encode(user.getPassword()
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void assignRoleToUser(String username, RoleName roleName) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList()));
//    }
}
