package ru.alexsolution.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import ru.alexsolution.entity.user.User;

import javax.servlet.annotation.MultipartConfig;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {

    private UUID id;

    private String title;

    private String shortTitle;

    private User author;

    private String description;

    private String region;

    private String country;

    private Integer level;

    private BigDecimal price;

    private BigDecimal length;

    private Integer duration;

    private String image;

    @EqualsAndHashCode.Exclude
    private Boolean isPurchared;

    @EqualsAndHashCode.Exclude
    private Boolean isFavorite;

}
