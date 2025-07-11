package nl.benjamin.muziekmarktplaats.dto;

import nl.benjamin.muziekmarktplaats.model.Order;

import java.util.List;

public class UserResponseDto {
    public Long id;
    public String email;
    public String password;
    public List<Order> orderList;
}
