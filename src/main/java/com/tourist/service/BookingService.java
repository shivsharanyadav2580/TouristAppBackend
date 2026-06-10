package com.tourist.service;

import com.tourist.dto.BookingRequest;
import com.tourist.dto.BookingResponse;
import com.tourist.dto.UserResponse;
import com.tourist.entity.Booking;
import com.tourist.entity.Tour;
import com.tourist.entity.User;
import com.tourist.repository.BookingRepository;
import com.tourist.repository.TourRepository;
import com.tourist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, TourRepository tourRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
    }

    public BookingResponse addBooking(BookingRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Tour tour = tourRepository.findById(request.tourId()).orElseThrow(() -> new IllegalArgumentException("Tour not found"));

        Double totalAmount = tour.getPrice() * request.numberOfPersons();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTour(tour);
        booking.setTravelDate(request.travelDate());
        booking.setNumberOfPersons(request.numberOfPersons());
        booking.setTotalAmount(totalAmount);
        booking.setBookingStatus("CONFIRMED");

        Booking savedBooking = bookingRepository.save(booking);
        return mapToResponse(savedBooking);

    }


    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        return mapToResponse(booking);
    }
    public String cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        booking.setBookingStatus("CANCELLED");
        bookingRepository.save(booking);
        return "Booking cancelled successfully";
    }

    private BookingResponse mapToResponse(Booking booking) {

        return new BookingResponse(
                booking.getId(),
                booking.getUser().getFullName(),
                booking.getTour().getTitle(),
                booking.getTravelDate(),
                booking.getNumberOfPersons(),
                booking.getTotalAmount(),
                booking.getBookingStatus()
        );

    }
}





