package nl.benjamin.muziekmarktplaats.controller;

import io.jsonwebtoken.Jwt;
import nl.benjamin.muziekmarktplaats.dto.ReviewRequestDto;
import nl.benjamin.muziekmarktplaats.dto.ReviewResponseDto;
import nl.benjamin.muziekmarktplaats.model.User;
import nl.benjamin.muziekmarktplaats.service.ReviewService;
import nl.benjamin.muziekmarktplaats.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;
    private final UserService userService;

    public ReviewController(ReviewService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDto> addReview(@RequestBody ReviewRequestDto reviewRequestDto, @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = userService.getUserByUsername(userDetails.getUsername()).id;
        ReviewResponseDto review = service.saveReview(reviewRequestDto, userId);

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
