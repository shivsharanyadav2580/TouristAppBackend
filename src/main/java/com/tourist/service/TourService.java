package com.tourist.service;

import com.tourist.TourManagementApplication;
import com.tourist.dto.TourRequestDto;
import com.tourist.dto.TourResponseDto;
import com.tourist.entity.Tour;
import com.tourist.exception.ResourceNotFoundException;
import com.tourist.repository.TourRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    private final TourRepository repository;

    public TourService(TourRepository repository) {
        this.repository = repository;
    }

    public List<TourResponseDto> getAllTours() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public Tour getTourById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tour not found"));
    }
    public List<Tour> searchByCity(String city) {
        return repository.findByCityContainingIgnoreCase(city);
    }

    public TourResponseDto createTour(TourRequestDto dto) {

        Tour tour = new Tour();
        tour.setTitle(dto.getTitle());
        tour.setCity(dto.getCity());
        tour.setDescription(dto.getDescription());
        tour.setPrice(dto.getPrice());
        tour.setDuration(dto.getDuration());
        tour.setImageUrl(dto.getImageUrl());

        Tour savedTour = repository.save(tour);

        return mapToDto(savedTour);
    }

    private TourResponseDto mapToDto(Tour tour) {

        TourResponseDto dto = new TourResponseDto();

        dto.setId(tour.getId());
        dto.setTitle(tour.getTitle());
        dto.setCity(tour.getCity());
        dto.setDescription(tour.getDescription());
        dto.setPrice(tour.getPrice());
        dto.setDuration(tour.getDuration());
        dto.setImageUrl(tour.getImageUrl());

        return dto;
    }

    public Page<Tour> getTours(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }
}