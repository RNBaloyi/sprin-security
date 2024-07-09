package com.discovery.spring_security.mapper;

import com.discovery.spring_security.dto.UserDto;
import com.discovery.spring_security.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{
    private final PasswordEncoder passwordEncoder;

    public UserMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if (userDto==null){
            return null;
        }

        return new User(
                userDto.getName(),
                userDto.getLastname(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword())

        );
    }


    @Override
    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}
