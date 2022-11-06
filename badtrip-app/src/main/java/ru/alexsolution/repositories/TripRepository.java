package ru.alexsolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.alexsolution.entity.Trip;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {

    @Query("from trip t where t.author.id = :uuid")
    List<Trip> findByAuthor(UUID uuid);

    List<Trip> findTripByIdIn(Set<UUID> uuids);

    List<Trip> findTripByIdNotIn(Set<UUID> uuids);

}
