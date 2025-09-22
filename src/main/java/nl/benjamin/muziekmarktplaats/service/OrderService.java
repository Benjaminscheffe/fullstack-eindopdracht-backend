package nl.benjamin.muziekmarktplaats.service;

import nl.benjamin.muziekmarktplaats.dto.OrderRequestDto;
import nl.benjamin.muziekmarktplaats.dto.OrderResponseDto;
import nl.benjamin.muziekmarktplaats.exception.RecordNotFoundException;
import nl.benjamin.muziekmarktplaats.mapper.OrderMapper;
import nl.benjamin.muziekmarktplaats.model.Beat;
import nl.benjamin.muziekmarktplaats.model.Order;
import nl.benjamin.muziekmarktplaats.model.User;
import nl.benjamin.muziekmarktplaats.repository.BeatRepository;
import nl.benjamin.muziekmarktplaats.repository.OrderRepository;
import nl.benjamin.muziekmarktplaats.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repos;
    private final BeatRepository beatRepository;
    private final UserRepository userRepository;
    private final OrderMapper mapper;

    public OrderService(OrderRepository repos, BeatRepository beatRepository, UserRepository userRepository, OrderMapper mapper) {
        this.repos = repos;
        this.beatRepository = beatRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public OrderResponseDto saveOrder(OrderRequestDto orderRequestDto) {
        Order order = mapper.toEntity(orderRequestDto);

        Order savedOrder = repos.save(order);

        repos.save(savedOrder);

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

    public void assignBeatToOrder(Long id, Long beatId) {
        Optional<Order> optionalOrder = repos.findById(id);
        Optional<Beat> optionalBeat = beatRepository.findById(beatId);

        if(optionalOrder.isPresent() && optionalBeat.isPresent()) {
            Order order = optionalOrder.get();
            Beat beat = optionalBeat.get();

            order.setBeat(beat);

            repos.save(order);
        } else {
            throw new RecordNotFoundException("No Order");
        }
    }

    public void assignUserToOrder(Long id, Long userId) {
        Optional<Order> optionalOrder = repos.findById(id);
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalOrder.isPresent() && optionalUser.isPresent()) {
            Order order = optionalOrder.get();
            User user = optionalUser.get();

            order.setUser(user);

            repos.save(order);
        }
    }
}
