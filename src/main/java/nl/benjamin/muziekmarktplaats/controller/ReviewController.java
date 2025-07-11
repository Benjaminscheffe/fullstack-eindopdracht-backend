package nl.benjamin.muziekmarktplaats.controller;

import nl.benjamin.muziekmarktplaats.dto.ReviewRequestDto;
import nl.benjamin.muziekmarktplaats.dto.ReviewResponseDto;
import nl.benjamin.muziekmarktplaats.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDto> addReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        ReviewResponseDto review = service.saveReview(reviewRequestDto);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + review.id).toUriString());

        return ResponseEntity.created(uri).body(review);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        List<ReviewResponseDto> reviews = service.getAllReviews();

        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable("id") Long id) {
        ReviewResponseDto review = service.getReviewById(id);

        return ResponseEntity.ok().body(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable("id") Long id, @RequestBody ReviewRequestDto reviewRequestDto) {
        ReviewResponseDto dto = service.updateReview(id, reviewRequestDto);

        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}/beat/{beatId}")
    public void assignBeatToReview(@PathVariable("id") Long id, @PathVariable("beatId") Long beatId) {
        service.assignBeatToReview(id, beatId);
    }



}
