package com.nexus.nexus_blog.service;

import com.nexus.nexus_blog.dto.UserRegistrationDto;
import com.nexus.nexus_blog.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User createUser(UserRegistrationDto userDto);

}
