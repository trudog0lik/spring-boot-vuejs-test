package de.jonashackt.springbootvuejs.domain;

import java.util.Set;
import java.util.UUID;

public class JwtResponse {
    private final String type = "Bearer";

    private String token;
    private UUID id;
    private String username;
    private Set<String> roles;

    public JwtResponse(String accessToken, UUID id, String username, Set<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}