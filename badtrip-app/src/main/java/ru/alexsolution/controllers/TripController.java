package ru.alexsolution.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.alexsolution.dto.TripDto;
import ru.alexsolution.entity.Trip;
import ru.alexsolution.services.TripService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trip")
@RequiredArgsConstructor
@Slf4j
public class TripController {

    private final TripService service;

    @GetMapping
    private List<Trip> getAllTrips(){
        return service.getAllTrips();
    }

    @GetMapping("/author/{id}")
    private TripDto findByAuthor(UUID id){
        return service.getTripByAuthor(id);
    }

    @GetMapping("/{id}")
    private Trip getTripById(@PathVariable UUID id){
        return service.findTripById(id);
    }

    @PostMapping
    private void createTrip(Principal principal, TripDto createTripDto){
        service.createTrip(principal, createTripDto);
    }



}
