package com.seuprojeto.gestaorequisicoes.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Map<String, String> users = new HashMap<>();

    static {
        // Usuários válidos (username, password)
        users.put("admin", "admin");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = users.get(username);
        if (password == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername(username).password("{noop}" + password).authorities("ROLE_USER").build();
    }
}
