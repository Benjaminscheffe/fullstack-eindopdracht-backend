package nl.benjamin.muziekmarktplaats.dto;

import nl.benjamin.muziekmarktplaats.model.Image;

public class BeatResponseUserDto {
    public Long id;
    public String title;
    public int bpm;
    public int price;
    public Image image;
    public Long userId;
}
