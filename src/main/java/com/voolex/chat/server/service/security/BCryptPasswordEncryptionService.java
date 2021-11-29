package com.voolex.chat.server.service.security;

import com.voolex.chat.server.service.security.PasswordEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordEncryptionService implements PasswordEncryptionService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String encode(String source) {
        return passwordEncoder.encode(source);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
