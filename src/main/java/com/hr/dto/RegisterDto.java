package com.hr.dto;

import lombok.Data;
import com.hr.model.ProfileRole;

import javax.validation.constraints.Email;

@Data
public class RegisterDto {
    private String login;
    private String password;
    private ProfileRole role;
    private String fio;
    @Email
    private String email;
}