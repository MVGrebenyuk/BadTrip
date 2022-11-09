package ru.alexsolution.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexsolution.dto.FilterDto;
import ru.alexsolution.services.TripService;

@RestController
@RequestMapping("/api/v1/filters")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "FILTER API", description = "Контроллер для работы с фильтрами")
public class FilterController {
    private final TripService tripService;

    @GetMapping
    @Operation(summary = "Получить все фильтры")
    private FilterDto getAllFilters(){
        return tripService.getAllFilters();
    }

}
