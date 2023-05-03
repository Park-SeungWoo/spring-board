package com.example.cloneboard.service.jwt;

import com.example.cloneboard.dao.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailImpl(
                userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Exists"))
        );
    }
}
