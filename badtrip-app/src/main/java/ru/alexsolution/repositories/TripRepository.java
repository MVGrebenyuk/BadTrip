package ru.alexsolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.alexsolution.entity.Trip;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> , JpaSpecificationExecutor<Trip>{

    @Query("from trip t where t.author.id = :uuid")
    List<Trip> findByAuthor(UUID uuid);

    List<Trip> findTripByIdIn(Set<UUID> uuids);

    List<Trip> findTripByIdNotIn(Set<UUID> uuids);

    @Query("select distinct t.country from trip t")
    List<String> findAllCountry();

    @Query("select distinct t.region from trip t")
    List<String> findAllRegions();

    @Query("select max(t.price) from trip t")
    BigDecimal findMaxPrice();

    @Query("select min(t.price) from trip t")
    BigDecimal findMinPrice();

    @Query("select max(t.duration) from trip t")
    Integer findMaxDuration();

    @Query("select min(t.duration) from trip t")
    Integer findMinDuration();

    @Query("select max(t.length) from trip t")
    BigDecimal findMaxlength();

    @Query("select min(t.length) from trip t")
    BigDecimal findMinlength();

}
