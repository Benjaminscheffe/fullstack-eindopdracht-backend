package nl.benjamin.muziekmarktplaats.service;

import nl.benjamin.muziekmarktplaats.dto.BeatResponseDto;
import nl.benjamin.muziekmarktplaats.mapper.BeatMapper;
import nl.benjamin.muziekmarktplaats.model.Beat;
//import nl.benjamin.muziekmarktplaats.repository.BeatRepository;
//import nl.benjamin.muziekmarktplaats.repository.OrderRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class BeatServiceTest {
//
//    @Mock
//    BeatRepository beatRepos;
//    BeatMapper mapper;
//
//    @InjectMocks
//    BeatService beatService;
//
//
//    @Test
//    @DisplayName("Should return correct beat")
//    void test1() {
//
//        // arrange
//        Beat beat = new Beat("Beat 2", 120, 12);
//        beat.setId(1L);
//
//        when(beatRepos.findById(1L)).thenReturn(Optional.of(beat));
//
//        // act
//        BeatResponseDto beatResponseDto = beatService.getBeatById(1L);
//
//        // assert
//        assertEquals("Beat 2", beatResponseDto.title);
//        assertEquals(120, beatResponseDto.bpm);
//        assertEquals(12, beatResponseDto.price);
//    }
//}