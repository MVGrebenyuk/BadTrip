package ru.alexsolution.repositories;

import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
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

    @Modifying
    @Query(value = "DELETE FROM purchased_to_user where trip_id in(?1);", nativeQuery = true)
    void deleteFromPurchasedById(UUID id);

    @Modifying
    @Query(value = "DELETE FROM favorites_to_user where trip_id in(?1);", nativeQuery = true)
    void deleteFromFavoriteById(UUID id);

    @Modifying
    @Query(value = "DELETE FROM trip where id in(?1)", nativeQuery = true)
    void deleteById(UUID id);

    @Query("select min(t.duration) from trip t")
    Integer findMinDuration();

    @Query("select max(t.length) from trip t")
    BigDecimal findMaxlength();

    @Query("select min(t.length) from trip t")
    BigDecimal findMinlength();

}
