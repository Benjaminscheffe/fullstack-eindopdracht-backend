package nl.benjamin.muziekmarktplaats.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BeatRequestDto {

    @NotBlank
    @Size(min=3, max=30)
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
