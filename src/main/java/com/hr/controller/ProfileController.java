package com.hr.controller;

import com.hr.dto.LoginDto;
import com.hr.dto.RegisterDto;
import com.hr.mapper.ProfileMapper;
import com.hr.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.hr.service.ProfileService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileMapper profileMapper;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterDto registerDto) {
        Profile profile = profileMapper.fromRegisterDto(registerDto);
        profileService.register(profile);
    }

    @PostMapping("/auth")
    public String auth(@RequestBody LoginDto dto) {
        return profileService.auth(dto.getLogin(), dto.getPassword());
    }

    @GetMapping("/me")
    public Profile me(@AuthenticationPrincipal Profile profile) {
//        return profile;
        return (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}