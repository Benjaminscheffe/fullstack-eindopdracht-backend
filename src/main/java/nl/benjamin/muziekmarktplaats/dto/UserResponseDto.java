package nl.benjamin.muziekmarktplaats.dto;

import nl.benjamin.muziekmarktplaats.model.Beat;
import nl.benjamin.muziekmarktplaats.model.Order;
import nl.benjamin.muziekmarktplaats.model.Role;

import java.util.List;
import java.util.Set;

public class UserResponseDto {
    public Long id;
    public String username;
    public String email;
    public String password;
    public List<Order> orderList;
    public List<Beat> beats;
    public Set<Role> roles;
}
