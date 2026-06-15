package com.tourist.controller;

import com.tourist.dto.ReviewRequestDto;
import com.tourist.entity.Review;
import com.tourist.entity.Tour;
import com.tourist.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }



    @PostMapping("/add")
    public ResponseEntity<Review> addReview(
            @RequestBody ReviewRequestDto dto) {

        return ResponseEntity.ok(
                reviewService.addReview(dto));
    }




}
