package de.jonashackt.springbootvuejs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class SignupRequest {
    private String username;
    private String password;
    private Set<String> roles;
}