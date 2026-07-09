package com.tourist.repository;

import com.tourist.entity.Booking;
import com.tourist.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BookingRepository
        extends JpaRepository<Booking, Long> {

    long count();

    long countByStatus(BookingStatus status);
    @Query("""
            SELECT COALESCE(SUM(b.totalAmount), 0)
            FROM Booking b
            WHERE b.status = 'CONFIRMED'
            """)
    BigDecimal getTotalRevenue();

}
