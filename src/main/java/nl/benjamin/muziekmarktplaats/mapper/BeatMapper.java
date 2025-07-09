package nl.benjamin.muziekmarktplaats.mapper;

import nl.benjamin.muziekmarktplaats.dto.BeatRequestDto;
import nl.benjamin.muziekmarktplaats.dto.BeatResponseDto;
import nl.benjamin.muziekmarktplaats.model.Beat;
import org.springframework.stereotype.Component;

@Component
public class BeatMapper {
    public Beat toEntity(BeatRequestDto beatRequestDto) {
        return new Beat(beatRequestDto.title, beatRequestDto.bpm, beatRequestDto.price);
    }

    public BeatResponseDto toResponseDto(Beat beat) {
        BeatResponseDto beatDto = new BeatResponseDto();
        beatDto.id = beat.getId();
        beatDto.title = beat.getTitle();
        beatDto.bpm = beat.getBpm();
        beatDto.price = beat.getPrice();
        beatDto.imageId = beat.getImage().getId();

        return beatDto;
    }
}
