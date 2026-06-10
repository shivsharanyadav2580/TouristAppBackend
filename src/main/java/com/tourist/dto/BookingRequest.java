package com.tourist.dto;

import java.time.LocalDate;

public record BookingRequest(

        Long tourId,
        Long userId,
        LocalDate travelDate,
        Integer numberOfPersons
) {
}
