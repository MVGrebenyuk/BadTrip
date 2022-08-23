package ru.alexsolution;

import org.mapstruct.Mapper;
import ru.alexsolution.dto.TripDto;
import ru.alexsolution.entity.Trip;

@Mapper(componentModel = "WannaGo")
public interface TripMapper {

    Trip toTrip(TripDto createTripDto);

    TripDto toCreateTripDto(Trip trip);

}
