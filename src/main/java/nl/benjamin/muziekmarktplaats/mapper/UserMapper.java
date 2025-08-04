package nl.benjamin.muziekmarktplaats.mapper;

import nl.benjamin.muziekmarktplaats.dto.UserRequestDto;
import nl.benjamin.muziekmarktplaats.dto.UserResponseDto;
import nl.benjamin.muziekmarktplaats.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final BeatMapper beatMapper;
    private final OrderMapper orderMapper;

    public UserMapper(BeatMapper beatMapper, OrderMapper orderMapper) {
        this.beatMapper = beatMapper;
        this.orderMapper = orderMapper;
    }

    public User toEntity(UserRequestDto userRequestDto) {

        return new User(userRequestDto.username, userRequestDto.email, userRequestDto.password);
    }

    public UserResponseDto toResponseDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.id = user.getId();
        userDto.username = user.getUsername();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();
//        if (user.getOrders() != null) {
//            userDto.orderList = orderMapper.toListResponseDto(user.getOrders());
//        }
//        if (user.getBeats() != null) {
//            userDto.beats = beatMapper.toListResponseUserDto(user.getBeats());
//        }

        userDto.roles = user.getRoles();

        return userDto;
    }
}
