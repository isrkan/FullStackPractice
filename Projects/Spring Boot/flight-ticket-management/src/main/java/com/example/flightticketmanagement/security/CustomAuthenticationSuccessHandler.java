package com.example.flightticketmanagement.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component // Indicates that this class should be automatically detected and registered as a bean in the application context
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler { // Defines a strategy used to handle successful authentication

    @Override
    // Handles the redirection of users based on their roles after successful authentication
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String redirectUrl = "/";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_USER")) {
                redirectUrl = "/account";
                break;
            } else if (authority.getAuthority().equals("ROLE_AIRLINE")) {
                redirectUrl = "/airline-flights";
                break;
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                redirectUrl = "/admin-page";
                break;
            }
        }

        response.sendRedirect(redirectUrl);
    }
}