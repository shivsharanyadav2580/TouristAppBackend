package com.tourist.service;

import com.tourist.entity.Location;
import com.tourist.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LocationService {
    @Autowired
   private LocationRepository locationRepository;


    public Location saveLocation(Location location){
        return  locationRepository.save(location);
    }

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id){
        return locationRepository.findById(id).orElseThrow(() -> new RuntimeException(("Location not found")));
    }

    public void deleteLocation(Long id){
        locationRepository.deleteById(id);
    }



}
