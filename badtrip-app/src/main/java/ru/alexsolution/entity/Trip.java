package ru.alexsolution.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Table
@Data
@NoArgsConstructor
@Entity(name = "trip")
public class Trip {

    @Id
    private UUID id;

    private String title;

    private String shortTitle;

    private UUID author;

    private String region;

    private String country;

    private String city;

    //private Photos photos;

    //private TripMap tripMap;

    private Integer level;

    private BigDecimal price;

    private BigDecimal length;

    private LocalTime duration;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

}
