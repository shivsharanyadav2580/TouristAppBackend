package com.tourist.repository;

import com.tourist.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository
        extends JpaRepository<Review, Long> {

    List<Review> findByTourId(Long tourId);

    @Query("""
SELECT COALESCE(AVG(r.rating),0)
FROM Review r
""")
    Double getAverageRating();
}