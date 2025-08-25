package nl.benjamin.muziekmarktplaats.service;

import nl.benjamin.muziekmarktplaats.dto.UserRequestDto;
import nl.benjamin.muziekmarktplaats.dto.UserResponseDto;
import nl.benjamin.muziekmarktplaats.exception.RecordNotFoundException;
import nl.benjamin.muziekmarktplaats.mapper.UserMapper;
import nl.benjamin.muziekmarktplaats.model.Role;
import nl.benjamin.muziekmarktplaats.model.User;
import nl.benjamin.muziekmarktplaats.repository.RoleRepository;
import nl.benjamin.muziekmarktplaats.repository.UserRepository;
import org.hibernate.jdbc.Expectation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepos;

    @Mock
    UserMapper mapper;

    @Mock
    RoleRepository roleRepos;

    @InjectMocks
    UserService userService;

    @Test
    void saveUser() {
        // Arrange
        User newuser = new User("Piet", "piet@hotmail.com","1234");
        newuser.setId(1L);
        Role role = new Role();
        role.setRolename("ROLE_USER");

        when(roleRepos.findById("ROLE_USER")).thenReturn(Optional.of(role));
        when(userRepos.save(any(User.class))).thenReturn(newuser);
        when(mapper.toEntity(any(UserRequestDto.class))).thenReturn(newuser);

        UserResponseDto expectedDto = new UserResponseDto();

        expectedDto.id = 1L;
        expectedDto.username = "Piet";
        expectedDto.email = "piet@hotmail.com";
        expectedDto.password = "1234";

        UserRequestDto requestDto = new UserRequestDto("Piet", "piet@hotmail.com", "1234");

        when(mapper.toResponseDto(any(User.class))).thenReturn(expectedDto);

        // Act
        UserResponseDto userResponseDto = userService.saveUser(requestDto);

        // Assert
        assertEquals("Piet", userResponseDto.username);
        assertEquals("piet@hotmail.com", userResponseDto.email);
        assertEquals("1234", userResponseDto.password);

    }

    @Test
    void getAllUsers() {
        // Arrange
        User user1= new User("Klaas", "klaas@hotmail.com", "1234");
        User user2= new User("Hans", "hans@hotmail.com", "1234");

        user1.setId(1L);
        user2.setId(2L);

        UserResponseDto expectedDto1 = new UserResponseDto();
        UserResponseDto expectedDto2 = new UserResponseDto();

        expectedDto1.id = 1L;
        expectedDto1.username = "Klaas";
        expectedDto1.email = "klaas@hotmail.com";
        expectedDto1.password = "1234";

        expectedDto2.id = 2L;
        expectedDto2.username = "Hans";
        expectedDto2.email = "hans@hotmail.com";
        expectedDto2.password = "1234";

        when(mapper.toResponseDto(user1)).thenReturn(expectedDto1);
        when(mapper.toResponseDto(user2)).thenReturn(expectedDto2);

        when(userRepos.findAll()).thenReturn(List.of(user1, user2));

        // Act
       List<UserResponseDto> usersFound = userService.getAllUsers();

        // Assert
        assertEquals(expectedDto1, usersFound.get(0));
        assertEquals(expectedDto2, usersFound.get(1));
    }

    @Test
    void getUserById() {
        // Arrange
        User user= new User("Klaas", "klaas@hotmail.com", "1234");

        user.setId(1L);

        when(userRepos.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDto expectedDto = new UserResponseDto();

        expectedDto.id = 1L;
        expectedDto.username = "Klaas";
        expectedDto.email = "klaas@hotmail.com";
        expectedDto.password = "1234";

        when(mapper.toResponseDto(user)).thenReturn(expectedDto);

        // Act
        UserResponseDto userResponseDto = userService.getUserById(1L);

        // Assert
        assertEquals("Klaas", userResponseDto.username);
        assertEquals("klaas@hotmail.com", userResponseDto.email);
        assertEquals("1234", userResponseDto.password);
    }

    @Test
    void getUserByIdFailure() {
        // Arrange
        when(userRepos.findById(1L)).thenReturn(Optional.empty());

        // Act - Assert
        assertThrows(RecordNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void updateUser() {
        // Arrange
        User user= new User("Klaas", "klaas@hotmail.com", "1234");
        user.setId(1L);

        UserRequestDto newUser = new UserRequestDto("Henk","henk@hotmail.com", "5678");

        when(userRepos.findById(1L)).thenReturn(Optional.of(user));
        when(userRepos.save(any(User.class))).thenReturn(user);

        UserResponseDto expectedDto = new UserResponseDto();

        expectedDto.id = 1L;
        expectedDto.username = "Henk";
        expectedDto.email = "henk@hotmail.com";
        expectedDto.password = "5678";

        when(mapper.toResponseDto(any(User.class))).thenReturn(expectedDto);

        // Act
        UserResponseDto userUpdateResponseDto = userService.updateUser(1L, newUser);

        // Assert
        assertEquals("Henk", userUpdateResponseDto.username);
        assertEquals("henk@hotmail.com", userUpdateResponseDto.email);
        assertEquals("5678", userUpdateResponseDto.password);
    }

    @Test
    void updateUserFailure() {
        // Arrange
        when(userRepos.findById(1L)).thenReturn(Optional.empty());
        UserRequestDto newUser = new UserRequestDto("Henk","henk@hotmail.com", "5678");

        // Act - Assert
        assertThrows(RecordNotFoundException.class, () -> userService.updateUser(1L, newUser));
    }
}