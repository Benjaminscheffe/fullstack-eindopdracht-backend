package nl.benjamin.muziekmarktplaats.mapper;

import nl.benjamin.muziekmarktplaats.dto.BeatRequestDto;
import nl.benjamin.muziekmarktplaats.dto.BeatResponseDto;
import nl.benjamin.muziekmarktplaats.dto.BeatResponseDtoCompact;
import nl.benjamin.muziekmarktplaats.model.Beat;
import nl.benjamin.muziekmarktplaats.model.User;
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

    public Beat toEntityWithUser(BeatRequestDto beatRequestDto, User user) {
        Beat beat = toEntity(beatRequestDto);

        beat.setUser(user);

        return beat;
    }

    public BeatResponseDto toResponseDto(Beat beat) {
        BeatResponseDto beatDto = new BeatResponseDto();
        beatDto.id = beat.getId();
        beatDto.title = beat.getTitle();
        beatDto.bpm = beat.getBpm();
        beatDto.price = beat.getPrice();
        if (beat.getImage() != null) {
            beatDto.image = beat.getImage();
        }
        if (beat.getBeatFile() != null) {
            beatDto.beatFile = beat.getBeatFile();
        }
        if (beat.getReviews() != null) {
            beatDto.reviews = reviewMapper.toListResponseDto(beat.getReviews());
        }
        beatDto.userName = beat.getUser().getUsername();

        return beatDto;
    }

    public BeatResponseDtoCompact toResponseUserDto(Beat beat) {
        BeatResponseDtoCompact beatDto = new BeatResponseDtoCompact();
        beatDto.id = beat.getId();
        beatDto.title = beat.getTitle();
        beatDto.bpm = beat.getBpm();
        beatDto.price = beat.getPrice();
        if (beat.getImage() != null) {
            beatDto.image = beat.getImage();
        }
        beatDto.userName = beat.getUser().getUsername();

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

    public List<BeatResponseDtoCompact> toListResponseUserDto(List<Beat> beatList) {
        List<BeatResponseDtoCompact> responseDtoUserList = new ArrayList<>();

        for (Beat beat : beatList) {
            BeatResponseDtoCompact responseDto = this.toResponseUserDto(beat);

            responseDtoUserList.add(responseDto);
        }

        return responseDtoUserList;
    }
}
