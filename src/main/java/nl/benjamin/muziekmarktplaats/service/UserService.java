package nl.benjamin.muziekmarktplaats.service;

import nl.benjamin.muziekmarktplaats.dto.UserRequestDto;
import nl.benjamin.muziekmarktplaats.dto.UserResponseDto;
import nl.benjamin.muziekmarktplaats.exception.RecordNotFoundException;
import nl.benjamin.muziekmarktplaats.mapper.UserMapper;
import nl.benjamin.muziekmarktplaats.model.User;
import nl.benjamin.muziekmarktplaats.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repos;
    private final UserMapper mapper;

    public UserService(UserRepository repos, UserMapper mapper) {
        this.repos = repos;
        this.mapper = mapper;
    }

    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        User user = mapper.toEntity(userRequestDto);

        User savedUser = repos.save(user);

        UserResponseDto dto = mapper.toResponseDto(savedUser);

        return dto;
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> userList = repos.findAll();
        List<UserResponseDto> userDtoList = new ArrayList<>();

        for(User user : userList) {
            UserResponseDto dto = mapper.toResponseDto(user);
            userDtoList.add(dto);
        }

        return userDtoList;
    }

    public UserResponseDto getUserById(Long id) {
        Optional<User> user = repos.findById(id);

        if (user.isPresent()) {
            return mapper.toResponseDto(user.get());
        } else {
            throw new RecordNotFoundException("No user with id " + id);
        }
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        Optional<User> userOptional = repos.findById(id);

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEmail(userRequestDto.getEmail());
            user.setPassword(userRequestDto.getPassword());


            User returnUser = repos.save(user);

            return mapper.toResponseDto(returnUser);
        } else {
            throw new RecordNotFoundException("No user with id " + id);
        }
    }
}
