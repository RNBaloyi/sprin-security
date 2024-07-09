package com.discovery.spring_security.controller;


import com.discovery.spring_security.dto.UserDto;
import com.discovery.spring_security.service.UserService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<?> getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String getAdminPage(){
        return "WELCOME ADMIN";
    }

}
