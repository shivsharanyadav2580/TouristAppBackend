package com.tourist.repository;

import com.tourist.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    List<Tour> findByLocation_CityContainingIgnoreCase(String city);
    List<Tour> findByTitleContainingIgnoreCase(String title);

    List<Tour> findByPriceBetween(Double minPrice, Double maxPrice);
}
