package ru.alexsolution.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "trip")
public class Trip {

    @Id
    private UUID id;

    private String title;

    private String shortTitle;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String region;

    private String country;

    private String city;

    //private Photos photos;

    //private TripMap tripMap;

    private Integer level;

    private BigDecimal price;

    private BigDecimal length;

    private LocalTime duration;

    private String image;

    private String description;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

}
