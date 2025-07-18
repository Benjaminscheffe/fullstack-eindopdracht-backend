package nl.benjamin.muziekmarktplaats.dto;

import nl.benjamin.muziekmarktplaats.model.Order;
import nl.benjamin.muziekmarktplaats.model.Review;
import nl.benjamin.muziekmarktplaats.model.User;

import java.util.List;

public class BeatResponseDto {
    public Long id;
    public String title;
    public int bpm;
    public int price;
    public Long imageId;
    public List<ReviewResponseDto> reviews;
    public Long userId;
}
