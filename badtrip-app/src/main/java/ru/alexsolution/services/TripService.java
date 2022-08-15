package ru.alexsolution.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexsolution.entity.Trip;
import ru.alexsolution.repositories.TripRepository;

import java.util.List;
import java.util.UUID;

@Service
@Data
@RequiredArgsConstructor
public class TripService {

    private final TripRepository repository;

    public List<Trip> getAllTrips(){
       return repository.findAll();
    }

    public Trip findTripById(UUID id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find trip"));
    }

}
