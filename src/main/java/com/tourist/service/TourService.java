package com.tourist.service;

import com.tourist.TourManagementApplication;
import com.tourist.dto.TourRequestDto;
import com.tourist.dto.TourResponseDto;
import com.tourist.entity.Location;
import com.tourist.entity.Tour;
import com.tourist.exception.ResourceNotFoundException;
import com.tourist.repository.LocationRepository;
import com.tourist.repository.TourRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    private final TourRepository repository;
    private final LocationRepository locationRepository;

    public TourService(TourRepository repository , LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
        this.repository = repository;
    }


    public Page<Tour> getAllTour(int page , int size){
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public Tour getTourById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tour not found"));
    }
    public List<Tour> searchByCity(String city) {
        return repository.findByLocation_CityContainingIgnoreCase(city);
    }

    public TourResponseDto createTour(TourRequestDto dto) {

        Location location = locationRepository.findById(dto.getLocationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Location not found"));

        Tour tour = new Tour();

        tour.setTitle(dto.getTitle());
        tour.setLocation(location);
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

        dto.setLocationId(tour.getLocation().getId());

        dto.setCity(tour.getLocation().getCity());

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

    public TourResponseDto updateTour(Long id, TourRequestDto dto) {

        Tour tour = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tour not found"));

        Location location = locationRepository.findById(dto.getLocationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Location not found"));

        tour.setTitle(dto.getTitle());
        tour.setLocation(location);
        tour.setDescription(dto.getDescription());
        tour.setPrice(dto.getPrice());
        tour.setDuration(dto.getDuration());
        tour.setImageUrl(dto.getImageUrl());

        Tour updatedTour = repository.save(tour);

        return mapToDto(updatedTour);
    }


    public List<Tour> searchTour(String title){
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public List<Tour> filterByPrice(
            Double minPrice,
            Double maxPrice) {

        return repository.findByPriceBetween(
                minPrice,
                maxPrice);
    }

    }




