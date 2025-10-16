package com.nexus.nexus_blog.service;

import com.nexus.nexus_blog.dto.UserRegistrationDto;
import com.nexus.nexus_blog.model.User;
import com.nexus.nexus_blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserRegistrationDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.username());
        newUser.setEmail(userDto.email());

        String encodedPassword = passwordEncoder.encode(userDto.password());
        newUser.setPassword(encodedPassword);

        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizador não encontrado: " + username));
    }
}
