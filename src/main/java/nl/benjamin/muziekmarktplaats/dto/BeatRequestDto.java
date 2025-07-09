package nl.benjamin.muziekmarktplaats.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BeatRequestDto {

    @NotBlank
    @Size(min=3, max=30)
    public String title;

    @NotBlank
    public int bpm;

    @NotBlank
    public int price;

    public String getTitle() {
        return title;
    }

    public int getBpm() {
        return bpm;
    }

    public int getPrice() {
        return price;
    }

}
