package ru.alexsolution.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.alexsolution.dto.TripDto;
import ru.alexsolution.entity.Trip;
import ru.alexsolution.entity.user.User;
import ru.alexsolution.entity.user.UserDetails;
import ru.alexsolution.repositories.TripRepository;
import ru.alexsolution.repositories.UserDetailsRepository;

import java.security.Principal;
import java.util.*;

@Service
@Data
@RequiredArgsConstructor
public class TripService {

    private final TripRepository repository;
    private final UserService userService;
    private final AwsService awsService;
    private final UserDetailsRepository userDetailsRepository;

    public List<Trip> getAllTrips(){
       return repository.findAll();
    }

    public Trip findTripById(UUID id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find trip"));
    }

    public void createTrip(Principal principal, TripDto createTripDto) {
        User user = userService.findByLogin(principal.getName())
                .orElseThrow(() -> new RuntimeException("Cannot find author"));
        Trip trip = toEntity(createTripDto, user);
        repository.save(trip);
    }

    public List<Trip> getTripByAuthor(UUID author){
        return repository.findByAuthor(author);
    }

    public TripDto toDto(Trip trip){
        return TripDto.builder()
                .id(UUID.randomUUID())
                .author(trip.getAuthor().getId())
                .country(trip.getCountry())
                .duration(trip.getDuration())
                .length(trip.getLength())
                .level(trip.getLevel())
                .description(trip.getDescription())
                .price(trip.getPrice())
                .region(trip.getRegion())
                .shortTitle(trip.getShortTitle())
                .title(trip.getTitle())
                .build();
    }

    public Trip toEntity(TripDto trip, User user){
        return Trip.builder()
                .id(UUID.randomUUID())
                .author(user)
                .country(trip.getCountry())
                .duration(trip.getDuration())
                .length(trip.getLength())
                .level(trip.getLevel())
                .price(trip.getPrice())
                .region(trip.getRegion())
                .description(trip.getDescription())
                .image(trip.getImage())
                .shortTitle(trip.getShortTitle())
                .title(trip.getTitle())
                .build();
    }

    public String saveGeneralImage(MultipartFile file) {
        return awsService.uploadImage(file);
    }

    public List<Trip> findFavoritesTours(String userName) {
       return (List<Trip>) userService.findByLogin(userName).get().getUserDetails().getFavorites();
    }

    public List<Trip> findPurchasedTours(String userName) {
        return (List<Trip>) userService.findByLogin(userName).get().getUserDetails().getPurchased();
    }

    @Transactional
    public void addToFavorite(String name, UUID tourId) {
        User user = userService.findByLogin(name).orElseThrow();
        UserDetails userDetails = user.getUserDetails();
       if(userDetails == null){
           List<Trip> tripList = new ArrayList<>(List.of(repository.findById(tourId).orElseThrow()));

           userDetails = new UserDetails();
           userDetails.setUser(userService.findByLogin(name).orElseThrow());
           userDetails.setFavorites(tripList);
           userDetailsRepository.save(userDetails);

           user.setUserDetails(userDetails);
       } else if(Optional.ofNullable(userDetails.getFavorites()).isPresent()){
           userDetails.getFavorites().add(repository.findById(tourId).orElseThrow());
           user.setUserDetails(userDetails);
       } else {
           List<Trip> tripList = new ArrayList<>(List.of(repository.findById(tourId).orElseThrow()));
           userDetails.setFavorites(tripList);
           user.setUserDetails(userDetails);
       }
    }

    @Transactional
    public void addToPurchased(String name, UUID tourId) {
        User user = userService.findByLogin(name).orElseThrow();
        UserDetails userDetails = user.getUserDetails();
        if(userDetails == null){
            List<Trip> tripList = new ArrayList<>(List.of(repository.findById(tourId).orElseThrow()));

            userDetails = new UserDetails();
            userDetails.setUser(userService.findByLogin(name).orElseThrow());
            userDetails.setPurchased(tripList);
            userDetailsRepository.save(userDetails);

            user.setUserDetails(userDetails);
        } else if(Optional.ofNullable(userDetails.getPurchased()).isPresent()){
            userDetails.getPurchased().add(repository.findById(tourId).orElseThrow());
            user.setUserDetails(userDetails);
        } else {
            List<Trip> tripList = new ArrayList<>(List.of(repository.findById(tourId).orElseThrow()));
            userDetails.setPurchased(tripList);
            user.setUserDetails(userDetails);
        }
    }
}
