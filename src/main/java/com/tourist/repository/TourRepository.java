package com.tourist.repository;

import com.tourist.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    List<Tour> findByCityContainingIgnoreCase(String city);
}
