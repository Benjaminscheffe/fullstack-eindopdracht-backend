package nl.benjamin.muziekmarktplaats.service;

import nl.benjamin.muziekmarktplaats.dto.*;
import nl.benjamin.muziekmarktplaats.exception.RecordNotFoundException;
import nl.benjamin.muziekmarktplaats.mapper.BeatMapper;
import nl.benjamin.muziekmarktplaats.mapper.ReviewMapper;
import nl.benjamin.muziekmarktplaats.model.Beat;
import nl.benjamin.muziekmarktplaats.model.Review;
import nl.benjamin.muziekmarktplaats.repository.BeatRepository;
import nl.benjamin.muziekmarktplaats.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    ReviewRepository reviewRepos;

    @Mock
    BeatRepository beatRepos;

    @Mock
    ReviewMapper mapper;

    @Mock
    BeatMapper beatMapper;

    @InjectMocks
    ReviewService reviewService;

    @Test
    void saveReview() {

        // Arrange
        Review newReview = new Review(10, "goed");
        newReview.setId(1L);

        when(reviewRepos.save(any(Review.class))).thenReturn(newReview);
        when(mapper.toEntity(any(ReviewRequestDto.class))).thenReturn(newReview);

        ReviewResponseDto expectedDto = new ReviewResponseDto();

        expectedDto.score = 10;
        expectedDto.comment = "goed";

        ReviewRequestDto requestDto = new ReviewRequestDto();

        requestDto.score = 10;
        requestDto.comment = "goed";

        when(mapper.toResponseDto(any(Review.class))).thenReturn(expectedDto);

        // Act
        ReviewResponseDto reviewResponseDto = reviewService.saveReview(requestDto);

        // Assert
        assertEquals(10, reviewResponseDto.score);
        assertEquals("goed", reviewResponseDto.comment);
    }

    @Test
    void getAllReviews() {
        // Arrange
        Review review1 = new Review(10, "goed");
        review1.setId(1L);

        Review review2 = new Review(7, "aardig");
        review2.setId(1L);

        ReviewResponseDto expectedDto1 = new ReviewResponseDto();

        expectedDto1.score = 10;
        expectedDto1.comment = "goed";

        ReviewResponseDto expectedDto2 = new ReviewResponseDto();

        expectedDto2.score = 7;
        expectedDto2.comment = "aardig";

        when(mapper.toResponseDto(review1)).thenReturn(expectedDto1);
        when(mapper.toResponseDto(review2)).thenReturn(expectedDto2);

        when(reviewRepos.findAll()).thenReturn(List.of(review1, review2));

        // Act
        List<ReviewResponseDto> reviewsFound = reviewService.getAllReviews();

        // Assert
        assertEquals(expectedDto1, reviewsFound.get(0));
        assertEquals(expectedDto2, reviewsFound.get(1));
    }

    @Test
    void getReviewById() {
        // Arrange
        Review review = new Review(10, "goed");
        review.setId(1L);

        when(reviewRepos.findById(1L)).thenReturn(Optional.of(review));

        ReviewResponseDto expectedDto = new ReviewResponseDto();

        expectedDto.id = 1L;
        expectedDto.score = 10;
        expectedDto.comment = "goed";

        when(mapper.toResponseDto(review)).thenReturn(expectedDto);

        // Act
        ReviewResponseDto reviewResponseDto = reviewService.getReviewById(1L);

        // Assert
        assertEquals(10, reviewResponseDto.score);
        assertEquals("goed", reviewResponseDto.comment);
    }

    @Test
    void getReviewByIdFailure() {

        // Arrange

        when(reviewRepos.findById(1L)).thenReturn(Optional.empty());

        // Act - Assert
        assertThrows(RecordNotFoundException.class, () -> reviewService.getReviewById(1L));
    }

    @Test
    void updateReview() {
        // Arrange
        Review review = new Review(10, "goed");
        review.setId(1L);

        ReviewRequestDto newReview = new ReviewRequestDto();

        newReview.score = 6;
        newReview.comment = "mwah";

        when(reviewRepos.findById(1L)).thenReturn(Optional.of(review));
        when(reviewRepos.save(any(Review.class))).thenReturn(review);

        ReviewResponseDto expectedDto = new ReviewResponseDto();

        expectedDto.score = 6;
        expectedDto.comment = "mwah";

        when(mapper.toResponseDto(any(Review.class))).thenReturn(expectedDto);

        // Act
        ReviewResponseDto reviewUpdateResponseDto = reviewService.updateReview(1L, newReview);

        // Assert
        assertEquals(6, reviewUpdateResponseDto.score);
        assertEquals("mwah", reviewUpdateResponseDto.comment);
    }

    @Test
    void updateReviewFailure() {
        // Arrange
        when(reviewRepos.findById(1L)).thenReturn(Optional.empty());
        ReviewRequestDto newReview = new ReviewRequestDto();

        newReview.score = 9;
        newReview.comment = "heel goed";

        // Act - Assert
        assertThrows(RecordNotFoundException.class, () -> reviewService.updateReview(1L, newReview));
    }

    @Test
    void assignBeatToReview() {
        // Arrange
        Review review = new Review(10, "goed");
        review.setId(1L);
        Beat beat = new Beat("Beat", 122, 9);
        beat.setId(1L);

        when(reviewRepos.findById(1L)).thenReturn(Optional.of(review));
        when(beatRepos.findById(1L)).thenReturn(Optional.of(beat));

        //Act
        reviewService.assignBeatToReview(1L, 1L);

        // Assert
        assertEquals(1L, review.getBeat().getId());

    }

    @Test
    void assignBeatToReviewFailure() {
        //Arrange
        Beat beat = new Beat("Beat", 122, 9);
        beat.setId(1L);

        when(reviewRepos.findById(1L)).thenReturn(Optional.empty());
        when(beatRepos.findById(1L)).thenReturn(Optional.of(beat));

        // Act - Assert
        assertThrows(RecordNotFoundException.class, () -> reviewService.assignBeatToReview(1L, 1L));

    }



























}