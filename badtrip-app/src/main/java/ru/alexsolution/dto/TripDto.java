package ru.alexsolution.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private Boolean isPurchared;

    private Boolean isFavorite;

}
