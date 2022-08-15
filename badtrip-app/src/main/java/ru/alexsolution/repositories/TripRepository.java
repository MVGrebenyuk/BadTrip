package ru.alexsolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexsolution.entity.Trip;

import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
}
