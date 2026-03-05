package com.yuutoap.Appoiments.service.security.contract;

import com.yuutoap.Appoiments.model.security.User;
import com.yuutoap.Appoiments.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(r -> r.getName())
                                .toArray(String[]::new)
                )
                .build();
    }

}
