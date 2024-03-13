package com.mealtracker.controller;


import com.mealtracker.dto.UserDTO;


import com.mealtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/auth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {

        UserDTO userDTO = userService.getUserById(userId);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUser(){
        UserDTO userDTO = userService.getPrincipal();

        return ResponseEntity.ok(userDTO);
    }

}

