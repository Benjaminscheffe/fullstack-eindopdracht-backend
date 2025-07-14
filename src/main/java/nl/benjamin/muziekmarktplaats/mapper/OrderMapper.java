package nl.benjamin.muziekmarktplaats.mapper;

import nl.benjamin.muziekmarktplaats.dto.OrderRequestDto;
import nl.benjamin.muziekmarktplaats.dto.OrderResponseDto;
import nl.benjamin.muziekmarktplaats.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toEntity(OrderRequestDto orderRequestDto) {

        return new Order(orderRequestDto.orderDate);
    }

    public OrderResponseDto toResponseDto(Order order) {
        OrderResponseDto orderDto = new OrderResponseDto();
        orderDto.id = order.getId();
        orderDto.orderNumber = order.getOrderNumber();
        orderDto.orderDate = order.getOrderDate();
        orderDto.beatId = order.getBeat().getId();
        orderDto.userId = order.getUser().getId();

        return orderDto;
    }
}
