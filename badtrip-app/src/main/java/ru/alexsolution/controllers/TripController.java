package ru.alexsolution.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "TRIP API", description = "Контроллер для работы с трипами")
public class TripController {

    private final TripService service;

    @GetMapping
    @Operation(summary = "Получить все туры")
    private List<Trip> getAllTrips(){
        return service.getAllTrips();
    }

    @GetMapping("/author/{id}")
    @Operation(summary = "Получить тур по if автора")
    private TripDto findByAuthor(UUID id){
        return service.getTripByAuthor(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить тур по id")
    private Trip getTripById(@PathVariable UUID id){
        return service.findTripById(id);
    }

    @PostMapping
    @Operation(summary = "Создать тур")
    private void createTrip(Principal principal, TripDto createTripDto){
        service.createTrip(principal, createTripDto);
    }



}
