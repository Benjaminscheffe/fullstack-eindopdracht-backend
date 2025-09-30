package nl.benjamin.muziekmarktplaats.exception;

import nl.benjamin.muziekmarktplaats.dto.UserRequestDto;
import nl.benjamin.muziekmarktplaats.dto.UserResponseDto;
import nl.benjamin.muziekmarktplaats.model.User;

import java.util.Optional;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}

//void updateUser() {
//    // arrange
//    User username= new User("Klaas", "klaas@hotmail.com", "1234");
//    username.setId(1L);
//
//    UserRequestDto newUser = new UserRequestDto("henk@hotmail.com", "5678");
//
//    when(userRepos.findById(1L)).thenReturn(Optional.of(username));
//    when(userRepos.save(any(User.class))).thenReturn(username);
//
//    UserResponseDto expectedDto = new UserResponseDto();
//
//    expectedDto.id = 1L;
//    expectedDto.username = "Klaas";
//    expectedDto.email = "henk@hotmail.com";
//    expectedDto.password = "5678";
//
//    when(mapper.toResponseDto(any(User.class))).thenReturn(expectedDto);
//
//    // Act
//    UserResponseDto userUpdateResponseDto = userService.updateUser(1L, newUser);
//
//    // assert
//    assertEquals("Klaas", userUpdateResponseDto.username);
//    assertEquals("henk@hotmail.com", userUpdateResponseDto.email);
//    assertEquals("5678", userUpdateResponseDto.password);
//}