package ru.alexsolution.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alexsolution.dto.CreateTripDto;
import ru.alexsolution.entity.Trip;
import ru.alexsolution.services.TripService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripService service;

    @GetMapping
    private List<Trip> getAllTrips(){
        return service.getAllTrips();
    }

    @PostMapping
    private void createTrip(Principal principal, CreateTripDto createTripDto){
        service.createTrip(principal, createTripDto);
    }

    @GetMapping("/{id}")
    private Trip getTripById(@PathVariable UUID id){
        return service.findTripById(id);
    }

}
