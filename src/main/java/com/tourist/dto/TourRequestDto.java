package com.tourist.dto;





import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TourRequestDto {

    @NotBlank
    private String title;

    private Long locationId;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @Positive
    private Integer duration;

    private String imageUrl;

    // getters setters


}