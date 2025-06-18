package ru.alexsolution.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexsolution.dto.PointDto;
import ru.alexsolution.services.PointService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/points")
@Schema(name = "Контроллер точек")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @GetMapping()
    @Operation(description = "Получение всех точек на карте")
    public List<PointDto> getAllPoints(){
        return pointService.getAllPoints();
    }

    @PostMapping
    @Operation(description = "Сохранение добавленной точки")
    public void savePoint(@RequestBody PointDto point, Principal principal){
        pointService.savePoint(point, principal != null ? principal.getName() : Strings.EMPTY);
    }

    @PutMapping("/{pointId}/update")
    @Operation(description = "Сохранение добавленной точки")
    public void updatePoint(@RequestBody PointDto point, Principal principal){
        pointService.updatePoint(point, principal != null ? principal.getName() : Strings.EMPTY);
    }

}
