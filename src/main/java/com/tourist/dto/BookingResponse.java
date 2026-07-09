package com.tourist.dto;

import java.time.LocalDate;

public record BookingResponse(

        Long bookingId,

        String userName,

        String tourTitle,

        LocalDate travelDate,

        Integer numberOfPersons,

        Double totalAmount,

        com.tourist.entity.BookingStatus bookingStatus
) {
}
