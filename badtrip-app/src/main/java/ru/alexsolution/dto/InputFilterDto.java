package ru.alexsolution.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class InputFilterDto {

    private String country;

    private String region;

    private String city;

    private BigDecimal priceMin;

    private BigDecimal priceMax;

    private BigDecimal lengthMin;

    private BigDecimal lengthMax;

    private Integer durationMin;

    private Integer durationMax;

    private Integer level;


}
