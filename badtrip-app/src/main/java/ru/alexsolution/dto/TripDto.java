package ru.alexsolution.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class TripDto {

    private UUID id;

    private String title;

    private String shortTitle;

    private UUID author;

    private String region;

    private String country;

    private Integer level;

    private BigDecimal price;

    private BigDecimal length;

    private LocalTime duration;


}
