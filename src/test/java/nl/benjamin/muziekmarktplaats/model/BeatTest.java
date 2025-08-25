package nl.benjamin.muziekmarktplaats.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeatTest {

    private Beat beat;
    private Image image = new Image("image.png");
    private BeatFile beatFile = new BeatFile("track.mp3");

    //arrange
    @BeforeEach
    void setup() {
        beat = new Beat("Beat 1", 122, 10, image, beatFile);
    }

    @Test
    void shouldKeepBeatName() {
        // act
        String result = beat.getTitle();

        // assert
        assertEquals("Beat 1", result);
    }

    @Test
    void shouldKeepBeatBpm() {
        // act
        int result = beat.getBpm();

        // assert
        assertEquals(122, result);
    }

    @Test
    void shouldKeepBeatPrice() {
        // act
        int result = beat.getPrice();

        // assert
        assertEquals(10, result);
    }

    @Test
    void shouldKeepImageFileName() {
        // act
        String result = beat.getImage().getFileName();

        // assert
        assertEquals("image.png", result);
    }

    @Test
    void shouldKeepBeatFileName() {
        // act
        String result = beat.getBeatFile().getFileName();

        // assert
        assertEquals("track.mp3", result);
    }
}