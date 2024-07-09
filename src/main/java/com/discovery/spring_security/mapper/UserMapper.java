package com.discovery.spring_security.mapper;

import com.discovery.spring_security.dto.UserDto;
import com.discovery.spring_security.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface UserMapper {

    User toEntity(UserDto userDto);
    UserDto toDto(User user);
}
