package nl.benjamin.muziekmarktplaats.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    private Review review;

    //arrange
    @BeforeEach
    void setup() {
        review = new Review(9, "heel goed");
    }

    @Test
    void shouldKeepReviewScore() {
        // act
        int result = review.getScore();

        // assert
        assertEquals(9, result);
    }

    @Test
    void shouldKeepReviewBpm() {
        // act
        String result = review.getComment();

        // assert
        assertEquals("heel goed", result);
    }

}