package nl.benjamin.muziekmarktplaats.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class OrderRequestDto {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")

    public LocalDateTime orderDate;

    public OrderRequestDto(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
