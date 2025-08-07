package nl.benjamin.muziekmarktplaats.dto;

import nl.benjamin.muziekmarktplaats.model.User;

public class ReviewResponseDto {
    public Long id;
    public int score;
    public String comment;
    public Long beatId;
    public String username;
}
