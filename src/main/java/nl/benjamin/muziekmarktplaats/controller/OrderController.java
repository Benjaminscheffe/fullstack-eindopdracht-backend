package nl.benjamin.muziekmarktplaats.controller;

import nl.benjamin.muziekmarktplaats.dto.OrderRequestDto;
import nl.benjamin.muziekmarktplaats.dto.OrderResponseDto;
import nl.benjamin.muziekmarktplaats.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto order = service.saveOrder(orderRequestDto);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + order.id).toUriString());

        return ResponseEntity.created(uri).body(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = service.getAllOrders();

        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable("id") Long id) {
        OrderResponseDto order = service.getOrderById(id);

        return ResponseEntity.ok().body(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable("id") Long id, @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto dto = service.updateOrder(id, orderRequestDto);

        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}/beat/{beatId}")
    public void assignBeatToOrder(@PathVariable("id") Long id, @PathVariable("beatId") Long beatId) {
        service.assignBeatToOrder(id, beatId);
    }

    @PutMapping("/{id}/user/{userId}")
    public void assignUserToOrder(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        service.assignUserToOrder(id, userId);
    }


}
