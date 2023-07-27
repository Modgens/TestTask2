package com.example.testingsystem.security.auth;

import com.example.testingsystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String first_name;
  private String last_name;
  private String login;
  private String password;
  private String role;
}
