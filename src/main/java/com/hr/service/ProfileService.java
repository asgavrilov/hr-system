package com.hr.service;

import com.hr.dao.ProfileRepository;
import com.hr.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.hr.security.JwtProvider;

@Component
public class ProfileService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return profileRepository.findByLogin(username);
    }

    @Retryable
    public void register(Profile profile) {
        profile.setPassword(encoder.encode(profile.getPassword()));
        profileRepository.save(profile);
    }

    public String auth(String login, String password) {
        Profile profile = profileRepository.findByLogin(login);
        if (encoder.matches(password, profile.getPassword())) {
            return jwtProvider.generateToken(login);
        }
        return null;
    }
}