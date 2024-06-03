package com.altun.conferencemanagementsystem.controller;

import com.altun.conferencemanagementsystem.entity.User;
import com.altun.conferencemanagementsystem.enums.RoleName;
import com.altun.conferencemanagementsystem.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/{username}/roles")
    public ResponseEntity<?> assignRoleToUser(@PathVariable String username, @RequestBody RoleName roleName) {
        userService.assignRoleToUser(username, roleName);
        return ResponseEntity.ok().build();
    }
}
