package com.tourist.service;

import com.tourist.dto.AdminDashboardResponse;
import com.tourist.entity.BookingStatus;
import com.tourist.repository.BookingRepository;
import com.tourist.repository.ReviewRepository;
import com.tourist.repository.TourRepository;
import com.tourist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final UserRepository userRepository;
    private final TourRepository tourRepository;
    private final BookingRepository bookingRepository;
    private final ReviewRepository reviewRepository;

    public AdminDashboardResponse getDashboard() {

        return AdminDashboardResponse.builder()
                .totalUsers(userRepository.count())
                .totalTours(tourRepository.count())
                .totalBookings(bookingRepository.count())
                .pendingBookings(bookingRepository.countByStatus(BookingStatus.PENDING))
                .confirmedBookings(bookingRepository.countByStatus(BookingStatus.CONFIRMED))
                .cancelledBookings(bookingRepository.countByStatus(BookingStatus.CANCELLED))
                .totalRevenue(bookingRepository.getTotalRevenue())
                .averageRating(reviewRepository.getAverageRating())
                .build();
    }
}