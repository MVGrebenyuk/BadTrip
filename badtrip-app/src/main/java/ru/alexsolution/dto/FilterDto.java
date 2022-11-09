package ru.alexsolution.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterDto {

    private List<String> country;

    private List<String> region;

    private List<String> city;

    private BigDecimal priceMin;

    private BigDecimal priceMax;

    private BigDecimal lengthMin;

    private BigDecimal lengthMax;

    private Integer durationMin;

    private Integer durationMax;

    private Integer level;

}
