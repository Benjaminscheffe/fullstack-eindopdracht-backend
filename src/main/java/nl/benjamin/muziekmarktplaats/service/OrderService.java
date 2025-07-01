package nl.benjamin.muziekmarktplaats.service;

import nl.benjamin.muziekmarktplaats.dto.OrderRequestDto;
import nl.benjamin.muziekmarktplaats.dto.OrderResponseDto;
import nl.benjamin.muziekmarktplaats.exception.RecordNotFoundException;
import nl.benjamin.muziekmarktplaats.mapper.OrderMapper;
import nl.benjamin.muziekmarktplaats.model.Order;
import nl.benjamin.muziekmarktplaats.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repos;
    private final OrderMapper mapper;

    public OrderService(OrderRepository repos, OrderMapper mapper) {
        this.repos = repos;
        this.mapper = mapper;
    }

    public OrderResponseDto saveOrder(OrderRequestDto orderRequestDto) {
        Order order = mapper.toEntity(orderRequestDto);

        Order savedOrder = repos.save(order);

        OrderResponseDto dto = mapper.toResponseDto(savedOrder);

        return dto;
    }

    public List<OrderResponseDto> getAllOrders() {
        List<Order> orderList = repos.findAll();
        List<OrderResponseDto> orderDtoList = new ArrayList<>();

        for(Order order : orderList) {
            OrderResponseDto dto = mapper.toResponseDto(order);
            orderDtoList.add(dto);
        }

        return orderDtoList;
    }

    public OrderResponseDto getOrderById(Long id) {
        Optional<Order> order = repos.findById(id);

        if (order.isPresent()) {
            return mapper.toResponseDto(order.get());
        } else {
            throw new RecordNotFoundException("No order with id " + id);
        }
    }

    public OrderResponseDto updateOrder(Long id, OrderRequestDto orderRequestDto) {
        Optional<Order> orderOptional = repos.findById(id);

        if(orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setOrderDate(orderRequestDto.getOrderDate());

            Order returnOrder = repos.save(order);

            return mapper.toResponseDto(returnOrder);
        } else {
            throw new RecordNotFoundException("No order with id " + id);
        }
    }
}
