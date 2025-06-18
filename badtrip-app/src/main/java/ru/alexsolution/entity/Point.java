package ru.alexsolution.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "point")
public class Point {

    @Id
    UUID id;

    String data;

    UUID authorId;
}
