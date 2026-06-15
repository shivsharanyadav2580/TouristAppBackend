package com.tourist.dto;

import lombok.Data;

@Data
public class TourResponseDto {

        private Long id;
        private String title;

        private Long locationId;
        private String city;

        private String description;
        private Double price;
        private Integer duration;
        private String imageUrl;
}