package nl.benjamin.muziekmarktplaats.mapper;

import nl.benjamin.muziekmarktplaats.dto.BeatRequestDto;
import nl.benjamin.muziekmarktplaats.dto.BeatResponseDto;
import nl.benjamin.muziekmarktplaats.model.Beat;
import org.springframework.stereotype.Component;

@Component
public class BeatMapper {
    public Beat toEntity(BeatRequestDto beatRequestDto) {
        return new Beat(beatRequestDto.title);
    }

    public BeatResponseDto toResponseDto(Beat beat) {
        BeatResponseDto beatDto = new BeatResponseDto();
        beatDto.id = beat.getId();
        beatDto.title = beat.getTitle();

        return beatDto;
    }
}
