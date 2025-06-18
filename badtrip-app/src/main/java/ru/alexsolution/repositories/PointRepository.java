package ru.alexsolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexsolution.entity.Point;

import java.util.UUID;

public interface PointRepository extends JpaRepository<Point, UUID> {
}
