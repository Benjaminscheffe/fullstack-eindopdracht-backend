package nl.benjamin.muziekmarktplaats.mapper;

import nl.benjamin.muziekmarktplaats.dto.BeatRequestDto;
import nl.benjamin.muziekmarktplaats.dto.BeatResponseDto;
import nl.benjamin.muziekmarktplaats.model.Beat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeatMapper {

    private final ReviewMapper reviewMapper;

    public BeatMapper(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

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
        beatDto.reviews = reviewMapper.toListResponseDto(beat.getReviews());
        beatDto.userId = beat.getUser().getId();

        return beatDto;
    }

    public List<BeatResponseDto> toListResponseDto(List<Beat> beatList) {
       List<BeatResponseDto> responseDtoList = new ArrayList<>();

       for (Beat beat : beatList) {
           BeatResponseDto responseDto = this.toResponseDto(beat);

           responseDtoList.add(responseDto);
       }

       return responseDtoList;
    }
}
