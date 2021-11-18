package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.configuration.JwtUtils;
import de.jonashackt.springbootvuejs.domain.JwtResponse;
import de.jonashackt.springbootvuejs.domain.LoginRequest;
import de.jonashackt.springbootvuejs.domain.SignupRequest;
import de.jonashackt.springbootvuejs.domain.User;
import de.jonashackt.springbootvuejs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", methods = RequestMethod.POST)
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private static final String USER_REGISTERED = "User registered successfully";

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();

        Set<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return ResponseEntity.ok().body(new JwtResponse(
                jwt,
                user.getId(),
                user.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest signUpRequest) {
        userService.saveUser(signUpRequest);
        return ResponseEntity.ok().body(USER_REGISTERED);
    }
}
