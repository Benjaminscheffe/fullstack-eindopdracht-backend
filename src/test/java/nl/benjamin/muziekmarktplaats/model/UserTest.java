package nl.benjamin.muziekmarktplaats.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;
    private Image image;
    private BeatFile beatFile;
    private Beat beat;

    //arrange
    @BeforeEach
    void setup() {
        image = new Image("image.png");
        beatFile = new BeatFile("track.mp3");
        beat = new Beat("Beat 1", 122, 10, image, beatFile);

        user = new User("Hans", "hans@hotmail.com", "1234");
    }

    @Test
    void shouldKeepUserName() {
        // act
        String result = user.getUsername();

        // assert
        assertEquals("Hans", result);
    }

    @Test
    void shouldKeepEmail() {
        // act
        String result = user.getEmail();

        // assert
        assertEquals("hans@hotmail.com", result);
    }

    @Test
    void shouldKeepPassword() {
        // act
        String result = user.getPassword();

        // assert
        assertEquals("1234", result);
    }




}