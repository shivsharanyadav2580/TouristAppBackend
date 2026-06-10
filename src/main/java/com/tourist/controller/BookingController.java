package com.tourist.controller;

import com.tourist.dto.BookingRequest;
import com.tourist.dto.BookingResponse;
import com.tourist.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping("/add")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        BookingResponse response = service.addBooking(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        BookingResponse response = service.getBookingById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cancle/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        service.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully");
    }
}
