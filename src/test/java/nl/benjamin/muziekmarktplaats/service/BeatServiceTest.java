package nl.benjamin.muziekmarktplaats.service;

import nl.benjamin.muziekmarktplaats.dto.BeatResponseDto;
import nl.benjamin.muziekmarktplaats.mapper.BeatMapper;
import nl.benjamin.muziekmarktplaats.mapper.ReviewMapper;
import nl.benjamin.muziekmarktplaats.model.Beat;
import nl.benjamin.muziekmarktplaats.model.BeatFile;
import nl.benjamin.muziekmarktplaats.model.Image;
import nl.benjamin.muziekmarktplaats.repository.BeatRepository;
import nl.benjamin.muziekmarktplaats.repository.ImageRepository;
import nl.benjamin.muziekmarktplaats.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BeatServiceTest {

    @Mock
    BeatRepository beatRepos;

    @Mock
    ImageRepository imageRepos;

    @Mock
    BeatMapper mapper;

    @InjectMocks
    BeatService beatService;

    @Test
    @DisplayName("Should return correct beat")
    void test1() {

        // arrange
        Beat beat = new Beat("Beat 2", 120, 12);
        beat.setId(1L);

        when(beatRepos.findById(1L)).thenReturn(Optional.of(beat));

        BeatResponseDto expectedDto = new BeatResponseDto();

        expectedDto.id = 1L;

        expectedDto.title = "Beat 2";

        expectedDto.bpm = 120;

        expectedDto.price = 12;

        when(beatRepos.findById(1L)).thenReturn(Optional.of(beat));

        when(mapper.toResponseDto(beat)).thenReturn(expectedDto);

        BeatMapper mapper = new BeatMapper(new ReviewMapper());

        // act
        BeatResponseDto beatResponseDto = beatService.getBeatById(1L);

        // assert
        assertEquals("Beat 2", beatResponseDto.title);
        assertEquals(120, beatResponseDto.bpm);
        assertEquals(12, beatResponseDto.price);
    }

//    void test2() {
//
//        // arrange
//        Image image = new Image("image.jpg");
//        BeatFile file = new BeatFile("track1.mp3");
//        Beat beat = new Beat("Beat 3", 120, 10);
//
//        beat.setId(1L);
//
//        when(beatRepos.findById(1L)).thenReturn(Optional.of(beat));
//
//        BeatResponseDto expectedDto = new BeatResponseDto();
//
//        expectedDto.id = 1L;
//
//        expectedDto.title = "Beat 2";
//
//        expectedDto.bpm = 120;
//
//        expectedDto.price = 12;
//
//        when(beatRepos.findById(1L)).thenReturn(Optional.of(beat));
//
//        when(imageRepos.findById("image.jpg")).thenReturn(Optional.of(image));
//
//        beat.setImage(image);
//
//        when(beatRepos.save(any(Beat.class))).thenReturn(beat)
//
//        BeatMapper mapper = new BeatMapper(new ReviewMapper());
//
//        // act
//        BeatResponseDto beatResponseDto = beatService.getBeatById(1L);
//
//        // assert
//        assertEquals("Beat 2", beatResponseDto.title);
//        assertEquals(120, beatResponseDto.bpm);
//        assertEquals(12, beatResponseDto.price);
//
//
//    }
}