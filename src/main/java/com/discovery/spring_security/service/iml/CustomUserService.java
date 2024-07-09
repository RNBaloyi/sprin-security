package com.discovery.spring_security.service.iml;


import com.discovery.spring_security.model.User;
import com.discovery.spring_security.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@AllArgsConstructor
@Component
public class CustomUserService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user==null){
            throw new UsernameNotFoundException("User not found");
        }



        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
        return new CustomUserDetails(user.getEmail(), user.getPassword(),
                Collections.singletonList(authority));

    }
}
