package com.tourist.controller;


import com.tourist.dto.TourRequestDto;
import com.tourist.dto.TourResponseDto;
import com.tourist.entity.Tour;
import com.tourist.service.TourService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {
    private final TourService service;

    public TourController(TourService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Tour> getTours(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size) {

        return service.getTours(page, size);
    }

    @PostMapping
    public TourResponseDto createTour(
            @Valid @RequestBody TourRequestDto dto) {

        return service.createTour(dto);
    }
    @GetMapping("/{id}")
    public Tour getTourById(@PathVariable Long id) {
        return service.getTourById(id);
    }
    @GetMapping("/search/city")
    public List<Tour> search(
            @RequestParam String city) {

        return service.searchByCity(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourResponseDto> updateTour(
            @PathVariable Long id,
            @RequestBody TourRequestDto dto) {

        return ResponseEntity.ok(
                service.updateTour(id, dto)
        );
    }




    @GetMapping("/search/title")
    public ResponseEntity<List<Tour>> searchTour(
            @RequestParam String title) {

        return ResponseEntity.ok(
                service.searchTour(title));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Tour>> filterByPrice(
            @RequestParam Double min,
            @RequestParam Double max) {

        return ResponseEntity.ok(
               service.filterByPrice(min, max));
    }





}
