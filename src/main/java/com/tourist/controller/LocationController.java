package com.tourist.controller;

import com.tourist.entity.Location;
import com.tourist.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<Location>
    createLocation(@RequestBody Location location){

        return ResponseEntity.ok(
                locationService.saveLocation(location)
        );
    }

    @GetMapping
    public ResponseEntity<List<Location>>
    getAllLocations(){

        return ResponseEntity.ok(
                locationService.getAllLocations()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location>
    getLocation(@PathVariable Long id){

        return ResponseEntity.ok(
                locationService.getLocationById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteLocation(@PathVariable Long id){

        locationService.deleteLocation(id);

        return ResponseEntity.ok("Deleted Successfully");
    }
}
