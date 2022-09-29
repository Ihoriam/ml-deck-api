package com.petproject.portfolio.user;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto getById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .map(UserDto::new)
                .orElseThrow(() -> new NotFoundException("User with id " + id + "does not exist"));
    }

    public UserDto getByUsername(String username) throws NotFoundException {
        return userRepository.findByUsername(username)
                .map(UserDto::new)
                .orElseThrow(() -> new NotFoundException("User with username " + username + "does not exist"));
    }

    public UserDto save(UserCommand userCommand) {
        return new UserDto(userRepository.save(
                new User(
                        userCommand.getUsername(),
                        passwordEncoder.encode(userCommand.getPassword()),
                        userCommand.getEmail(),
                        userCommand.getImageUrl()
                )
        ));
    }


}
