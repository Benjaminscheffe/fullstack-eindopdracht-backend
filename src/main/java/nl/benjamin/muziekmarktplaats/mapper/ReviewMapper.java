package nl.benjamin.muziekmarktplaats.mapper;

import nl.benjamin.muziekmarktplaats.dto.ReviewRequestDto;
import nl.benjamin.muziekmarktplaats.dto.ReviewResponseDto;
import nl.benjamin.muziekmarktplaats.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review toEntity(ReviewRequestDto reviewRequestDto) {

        return new Review(reviewRequestDto.score, reviewRequestDto.comment);
    }

    public ReviewResponseDto toResponseDto(Review review) {
        ReviewResponseDto reviewDto = new ReviewResponseDto();
        reviewDto.id = review.getId();
        reviewDto.score = review.getScore();
        reviewDto.comment = review.getComment();
        reviewDto.beatId = review.getBeat().getId();

        return reviewDto;
    }
}
