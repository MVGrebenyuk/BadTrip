package ru.alexsolution.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @GetMapping("/{id}/author")
    @Operation(summary = "Получить туры по id автора")
    private List<Trip> findByAuthor(@PathVariable UUID id){
        return service.getTripByAuthor(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить тур по id")
    private Trip getTripById(@PathVariable UUID id){
        return service.findTripById(id);
    }

    @PostMapping("/image")
    @Operation(summary = "Создать изображение")
    private String saveTripImage(Principal principal, @RequestBody MultipartFile file){
        return service.saveGeneralImage(file);
    }

    @GetMapping("/favorites")
    @Operation(summary = "Получить список избранных туров")
    private List<Trip> getFavoritesForUser(Principal principal){
        return service.findFavoritesTours(principal.getName());
    }

    @GetMapping("/purchased")
    @Operation(summary = "Получить список купленных туров")
    private List<Trip> getPurchasedForUser(Principal principal){
        return service.findPurchasedTours(principal.getName());
    }

    @PostMapping("/favorites/{tourId}")
    @Operation(summary = "Добавить тур в избранные")
    private void addToFavorite(Principal principal, @PathVariable UUID tourId){
        service.addToFavorite(principal.getName(), tourId);
    }

    @PostMapping("/purchased/{tourId}")
    @Operation(summary = "Добавить тур в кумленные")
    private void addToPurchased(Principal principal, @PathVariable UUID tourId){
        service.addToPurchased(principal.getName(), tourId);
    }

    @PostMapping
    @Operation(summary = "Создать тур")
    private void createTrip(Principal principal, @RequestBody TripDto trip){
        service.createTrip(principal, trip);
    }
}
