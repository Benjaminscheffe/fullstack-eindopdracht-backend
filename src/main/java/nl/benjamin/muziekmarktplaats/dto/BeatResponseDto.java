package nl.benjamin.muziekmarktplaats.dto;

import nl.benjamin.muziekmarktplaats.model.BeatFile;
import nl.benjamin.muziekmarktplaats.model.Image;

import java.util.List;

public class BeatResponseDto {
    public Long id;
    public String title;
    public int bpm;
    public int price;
    public Image image;
    public BeatFile beatFile;
    public List<ReviewResponseDto> reviews;
    public String userName;
}
