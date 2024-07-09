package com.discovery.spring_security.service.iml;


import com.discovery.spring_security.dto.UserDto;
import com.discovery.spring_security.mapper.UserMapper;
import com.discovery.spring_security.model.User;
import com.discovery.spring_security.repo.UserRepository;
import com.discovery.spring_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }



    @Override
    public ResponseEntity<?> getUserById(Long userId) {
       User user = userRepository.findById(userId).orElse(null);

       if (user==null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
       }

       UserDto userDto = userMapper.toDto(user);
       return ResponseEntity.ok(userDto);
    }

    @Override
    public ResponseEntity<?> saveUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }


}
