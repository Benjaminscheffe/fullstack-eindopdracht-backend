package nl.benjamin.muziekmarktplaats.dto;

import jakarta.validation.constraints.NotBlank;

public class ReviewRequestDto {

    @NotBlank
    public int score;

    @NotBlank
    public String comment;

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }
}
