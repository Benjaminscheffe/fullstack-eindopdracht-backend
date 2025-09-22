package nl.benjamin.muziekmarktplaats.controller;

import nl.benjamin.muziekmarktplaats.dto.UserRequestDto;
import nl.benjamin.muziekmarktplaats.dto.UserResponseDto;
import nl.benjamin.muziekmarktplaats.service.UserService;
import nl.benjamin.muziekmarktplaats.utils.JwtUtil;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final JwtUtil jwtUtil;

    public UserController(UserService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto user = service.saveUser(userRequestDto);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + user.id).toUriString());

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = service.getAllUsers();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails jwt) {

        UserResponseDto user = service.getUserById(id);

        System.out.println(jwt);

        if (user.username.equals(jwt.getUsername())) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto dto = service.updateUser(id, userRequestDto);

        return ResponseEntity.ok().body(dto);
    }


}
