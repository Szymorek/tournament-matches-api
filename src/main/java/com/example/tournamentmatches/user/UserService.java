package com.example.tournamentmatches.user;

import com.example.tournamentmatches.exception.UserByEmailNotFoundException;
import com.example.tournamentmatches.exception.UserByIdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserDto> getAllUsersDto() {
        return getAllUsers()
                .stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }


    private User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserByEmailNotFoundException(email));
    }

    public UserDto getUserDtoByEmail(String email) {
        return convertToUserDto(getUserByEmail(email));
    }

    private User getUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserByIdNotFoundException(id));
    }

    public UserDto getUserDtoById(Long id) {
        return convertToUserDto(getUserById(id));
    }


    private UserDto convertToUserDto(User user) {
        return new UserDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "user with username " + username + " does not exists"
                ));
    }


    public UserDto register(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return convertToUserDto(user);
    }
}
