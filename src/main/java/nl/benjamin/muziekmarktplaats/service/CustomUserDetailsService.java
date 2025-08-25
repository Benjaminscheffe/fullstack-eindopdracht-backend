package nl.benjamin.muziekmarktplaats.service;


import nl.benjamin.muziekmarktplaats.dto.UserResponseDto;
import nl.benjamin.muziekmarktplaats.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    /*TODO inject userservice */
    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    public UserDetails loadUserById(Long id) {
        UserResponseDto userDto = userService.getUserById(id);


        String password = userDto.password;

        Set<Role> authorities = userDto.roles;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role: authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        return new org.springframework.security.core.userdetails.User(userDto.username, password, grantedAuthorities);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponseDto userDto = userService.getUserByUsername(username);


        String password = userDto.password;

        Set<Role> authorities = userDto.roles;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role: authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        return new org.springframework.security.core.userdetails.User(userDto.username, password, grantedAuthorities);
    }
}