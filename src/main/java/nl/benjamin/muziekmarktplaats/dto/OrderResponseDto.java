package nl.benjamin.muziekmarktplaats.dto;


import java.time.LocalDateTime;
import java.util.Date;

public class OrderResponseDto {
    public Long id;
    public LocalDateTime orderDate;
    public BeatResponseDto beat;
    public Long userId;
}
