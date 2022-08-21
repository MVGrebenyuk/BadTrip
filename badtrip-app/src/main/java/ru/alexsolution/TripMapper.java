package ru.alexsolution;

import org.mapstruct.Mapper;
import ru.alexsolution.dto.CreateTripDto;
import ru.alexsolution.entity.Trip;

@Mapper(componentModel = "WannaGo")
public interface TripMapper {

    Trip toTrip(CreateTripDto createTripDto);

    CreateTripDto toCreateTripDto(Trip trip);

}
