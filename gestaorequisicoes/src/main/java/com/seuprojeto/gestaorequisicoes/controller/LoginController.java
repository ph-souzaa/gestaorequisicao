package com.seuprojeto.gestaorequisicoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            if (authentication.isAuthenticated()) {
                Cookie authCookie = new Cookie("authCookie", "validAuthToken");
                authCookie.setHttpOnly(true);
                authCookie.setMaxAge(60 * 60); // 1 hora
                response.addCookie(authCookie);
                return "Login successful";
            }
        } catch (AuthenticationException e) {
            return "Invalid credentials";
        }
        return "Invalid credentials";
    }
}
