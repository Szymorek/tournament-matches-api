package com.example.tournamentmatches.user;

import antlr.Token;
import com.example.tournamentmatches.jwt.JwtTokenUtil;
import com.example.tournamentmatches.jwt.LoginRequest;
import com.example.tournamentmatches.jwt.TokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsersDto() {
        return userService.getAllUsersDto();
    }

    @GetMapping(path = "{userId}")
    public UserDto getUserDtoById(@PathVariable Long userId) {
        return userService.getUserDtoById(userId);
    }

    @PostMapping(path = "register")
    public UserDto register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping(value = "login", consumes = "application/json")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            User user = (User) authentication.getPrincipal();

            return ResponseEntity.ok()
                    .body(new TokenResponse(jwtTokenUtil.generateAccessToken(user)));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
