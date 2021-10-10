package com.voolex.chat.server.service.impl;

import com.voolex.chat.server.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceSimple implements AuthenticationService {
    @Override
    public boolean attemptAuthenticate(UserDetails userDetails, String password) {
        if(password.equals(userDetails.getPassword())) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            return true;
        } else {
            throw new BadCredentialsException("wrong username or password");
        }
    }
}
