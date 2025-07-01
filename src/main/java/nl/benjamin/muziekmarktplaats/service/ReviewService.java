package nl.benjamin.muziekmarktplaats.service;

import nl.benjamin.muziekmarktplaats.dto.ReviewRequestDto;
import nl.benjamin.muziekmarktplaats.dto.ReviewResponseDto;
import nl.benjamin.muziekmarktplaats.exception.RecordNotFoundException;
import nl.benjamin.muziekmarktplaats.mapper.ReviewMapper;
import nl.benjamin.muziekmarktplaats.model.Review;
import nl.benjamin.muziekmarktplaats.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository repos;
    private final ReviewMapper mapper;

    public ReviewService(ReviewRepository repos, ReviewMapper mapper) {
        this.repos = repos;
        this.mapper = mapper;
    }

    public ReviewResponseDto saveReview(ReviewRequestDto reviewRequestDto) {
        Review review = mapper.toEntity(reviewRequestDto);

        Review savedReview = repos.save(review);

        ReviewResponseDto dto = mapper.toResponseDto(savedReview);

        return dto;
    }

    public List<ReviewResponseDto> getAllReviews() {
        List<Review> reviewList = repos.findAll();
        List<ReviewResponseDto> reviewDtoList = new ArrayList<>();

        for(Review review : reviewList) {
            ReviewResponseDto dto = mapper.toResponseDto(review);
            reviewDtoList.add(dto);
        }

        return reviewDtoList;
    }

    public ReviewResponseDto getReviewById(Long id) {
        Optional<Review> review = repos.findById(id);

        if (review.isPresent()) {
            return mapper.toResponseDto(review.get());
        } else {
            throw new RecordNotFoundException("No review with id " + id);
        }
    }

    public ReviewResponseDto updateReview(Long id, ReviewRequestDto reviewRequestDto) {
        Optional<Review> reviewOptional = repos.findById(id);

        if(reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            review.setScore(reviewRequestDto.getScore());
            review.setComment(reviewRequestDto.getComment());

            Review returnReview = repos.save(review);

            return mapper.toResponseDto(returnReview);
        } else {
            throw new RecordNotFoundException("No review with id " + id);
        }
    }
}
