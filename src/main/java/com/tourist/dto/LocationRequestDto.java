package com.tourist.dto;

import lombok.Data;

@Data
public class LocationRequestDto {

    private String city;
    private String state;
    private String country;
    private String description;
}
