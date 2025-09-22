package nl.benjamin.muziekmarktplaats.service;
import jakarta.transaction.Transactional;
import nl.benjamin.muziekmarktplaats.dto.UserRequestDto;
import nl.benjamin.muziekmarktplaats.dto.UserResponseDto;
import nl.benjamin.muziekmarktplaats.exception.RecordNotFoundException;
import nl.benjamin.muziekmarktplaats.mapper.UserMapper;
import nl.benjamin.muziekmarktplaats.model.User;
import nl.benjamin.muziekmarktplaats.repository.RoleRepository;
import nl.benjamin.muziekmarktplaats.repository.UserRepository;
import static nl.benjamin.muziekmarktplaats.config.SpringSecurityConfig.passwordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repos;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    //private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repos, RoleRepository roleRepository, UserMapper mapper) {
        this.repos = repos;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        //this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        User user = mapper.toEntity(userRequestDto);

        user.getRoles().add(roleRepository.findById("ROLE_USER").orElseThrow(() -> new RuntimeException()));
        user.setPassword(passwordEncoder().encode(user.getPassword()));

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

    @Transactional
    public UserResponseDto getUserById(Long id) {
        Optional<User> user = repos.findById(id);

        if (user.isPresent()) {
            return mapper.toResponseDto(user.get());
        } else {
            throw new RecordNotFoundException("No user with id " + id);
        }
    }

    @Transactional
    public UserResponseDto getUserByUsername(String username) {
        Optional<User> user = repos.findByUsername(username);

        if (user.isPresent()) {
            return mapper.toResponseDto(user.get());
        } else {
            throw new RecordNotFoundException("No user with name " + username);
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
