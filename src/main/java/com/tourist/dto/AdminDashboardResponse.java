package com.tourist.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardResponse {

    private long totalUsers;
    private long totalTours;
    private long totalBookings;

    private long pendingBookings;
    private long confirmedBookings;
    private long cancelledBookings;

    private BigDecimal totalRevenue;

    private double averageRating;
}