package com.tourist.service;

import com.tourist.dto.ReviewRequestDto;
import com.tourist.entity.Review;
import com.tourist.entity.Tour;
import com.tourist.entity.User;
import com.tourist.repository.ReviewRepository;
import com.tourist.repository.TourRepository;
import com.tourist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    public ReviewService(UserRepository userRepository, TourRepository tourRepository , ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
        this.reviewRepository = reviewRepository;

    }

    private final UserRepository userRepository;
    private final TourRepository tourRepository;
    private ReviewRepository reviewRepository;

    public Review addReview(ReviewRequestDto dto){
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));


        Tour tour = tourRepository.findById(dto.getTourId()).orElseThrow(() -> new RuntimeException("tour not found"));
    Review review = new Review();

    review.setRating(dto.getRating());
    review.setComment(dto.getComment());
    review.setUser(user);
    review.setTour(tour);

    return reviewRepository.save(review);

    }
}
