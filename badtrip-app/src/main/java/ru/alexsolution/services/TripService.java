package ru.alexsolution.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexsolution.dto.TripDto;
import ru.alexsolution.entity.Trip;
import ru.alexsolution.entity.User;
import ru.alexsolution.repositories.TripRepository;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
@Data
@RequiredArgsConstructor
public class TripService {

    private final TripRepository repository;
    private final UserService userService;

    public List<Trip> getAllTrips(){
       return repository.findAll();
    }

    public Trip findTripById(UUID id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find trip"));
    }

    public void createTrip(Principal principal, TripDto createTripDto) {
        Trip trip = toEntity(createTripDto);
        User user = userService.findByLogin(principal.getName())
                .orElseThrow(() -> new RuntimeException("Cannot find author"));
        trip.setAuthor(user);
        repository.save(trip);
    }

    public TripDto getTripByAuthor(UUID author){
        return toDto(repository.findByAuthor(author));
    }

    public TripDto toDto(Trip trip){
        return TripDto.builder()
                .id(UUID.randomUUID())
                .author(trip.getAuthor().getId())
                .country(trip.getCountry())
                .duration(trip.getDuration())
                .length(trip.getLength())
                .level(trip.getLevel())
                .price(trip.getPrice())
                .region(trip.getRegion())
                .shortTitle(trip.getShortTitle())
                .title(trip.getTitle())
                .build();
    }

    public Trip toEntity(TripDto trip){
        return Trip.builder()
                .id(UUID.randomUUID())
                .author(userService.findByID(trip.getAuthor()))
                .country(trip.getCountry())
                .duration(trip.getDuration())
                .length(trip.getLength())
                .level(trip.getLevel())
                .price(trip.getPrice())
                .region(trip.getRegion())
                .shortTitle(trip.getShortTitle())
                .title(trip.getTitle())
                .build();
    }
}
