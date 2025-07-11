package nl.benjamin.muziekmarktplaats.mapper;

import nl.benjamin.muziekmarktplaats.dto.UserRequestDto;
import nl.benjamin.muziekmarktplaats.dto.UserResponseDto;
import nl.benjamin.muziekmarktplaats.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDto userRequestDto) {

        return new User(userRequestDto.email, userRequestDto.password);
    }

    public UserResponseDto toResponseDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.id = user.getId();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();
        userDto.orderList = user.getOrders();

        return userDto;
    }
}
