package com.petproject.portfolio.user;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getById(@PathVariable String username) throws NotFoundException {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @PostMapping()
    public ResponseEntity<UserDto> create(UserCommand userCommand) {
        return ResponseEntity.ok(userService.save(userCommand));
    }
}
