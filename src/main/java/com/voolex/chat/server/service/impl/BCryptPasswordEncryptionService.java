package com.voolex.chat.server.service.impl;

import com.voolex.chat.server.service.PasswordEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
