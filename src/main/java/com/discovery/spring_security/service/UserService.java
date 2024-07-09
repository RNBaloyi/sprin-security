package com.discovery.spring_security.service;


import com.discovery.spring_security.dto.UserDto;
import com.discovery.spring_security.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

//    String  saveUser(UserDto userDto);
    ResponseEntity<?> getUserById(Long userId);

    ResponseEntity<?> saveUser(UserDto userDto);
}
