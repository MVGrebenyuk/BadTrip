package ru.alexsolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.alexsolution.entity.Trip;

import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {

    @Query("from trip t where t.author = :uuid")
    Trip findByAuthor(UUID uuid);

}
