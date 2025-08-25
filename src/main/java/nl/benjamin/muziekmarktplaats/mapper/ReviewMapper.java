package nl.benjamin.muziekmarktplaats.mapper;

import nl.benjamin.muziekmarktplaats.dto.ReviewRequestDto;
import nl.benjamin.muziekmarktplaats.dto.ReviewResponseDto;
import nl.benjamin.muziekmarktplaats.model.Beat;
import nl.benjamin.muziekmarktplaats.model.Review;
import nl.benjamin.muziekmarktplaats.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapper {

    public Review toEntity(ReviewRequestDto reviewRequestDto, Long userId) {
        Beat beat = new Beat();
        User user = new User();

        beat.setId(reviewRequestDto.beatId);
        user.setId(userId);

        return new Review(reviewRequestDto.score, reviewRequestDto.comment, beat, user);
    }

    public ReviewResponseDto toResponseDto(Review review) {
        ReviewResponseDto reviewDto = new ReviewResponseDto();
        reviewDto.id = review.getId();
        reviewDto.score = review.getScore();
        reviewDto.comment = review.getComment();
        reviewDto.beatId = review.getBeat().getId();
        reviewDto.username = review.getUser().getUsername();

        return reviewDto;
    }

    public List<ReviewResponseDto> toListResponseDto(List<Review> reviewList) {
        List<ReviewResponseDto> responseDtoList = new ArrayList<>();

        for (Review review : reviewList) {
            ReviewResponseDto responseDto = this.toResponseDto(review);

            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }
}
