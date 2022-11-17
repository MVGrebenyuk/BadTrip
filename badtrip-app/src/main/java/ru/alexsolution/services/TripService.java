package ru.alexsolution.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.alexsolution.dto.FilterDto;
import ru.alexsolution.dto.InputFilterDto;
import ru.alexsolution.dto.TripDto;
import ru.alexsolution.entity.Trip;
import ru.alexsolution.entity.user.User;
import ru.alexsolution.entity.user.UserDetails;
import ru.alexsolution.repositories.TripRepository;
import ru.alexsolution.repositories.UserDetailsRepository;
import ru.alexsolution.repositories.specifications.TripSpecification;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class TripService {

    private final TripRepository repository;
    private final UserService userService;
    private final AwsService awsService;
    private final UserDetailsRepository userDetailsRepository;

    public Set<TripDto> getAllTrips(Principal principal, InputFilterDto filter){
        Specification<Trip> specification = Specification.where(null);

        if(!ObjectUtils.isEmpty(filter)){
            specification = TripSpecification.fillSpecification(filter);
        }

        if(principal == null) {
            return repository.findAll(specification).stream().map(this::toDto).collect(Collectors.toSet());
        } else {
            return getFilteredTrips(principal, specification);
        }
    }


    public Set<TripDto> getFilteredTrips(Principal principal, Specification<Trip> specification){
        Set<TripDto> returningList = new HashSet<>();

        returningList.addAll(findFavoritesTours(principal.getName()).stream()
                .peek(t -> t.setIsFavorite(true))
                .collect(Collectors.toList()));
        returningList.addAll(findPurchasedTours(principal.getName()).stream().map(this::toDto)
                .peek(t -> t.setIsPurchared(true))
                .collect(Collectors.toList()));

        Set<TripDto> secRetList = repository.findAll(specification).stream().map(this::toDto).collect(Collectors.toSet());
        secRetList.forEach( s -> {
            returningList.forEach(r -> {
                if(s.equals(r)){
                    s.setIsPurchared(r.getIsPurchared());
                    s.setIsFavorite(r.getIsFavorite());
                }
            });
        });
        return secRetList;
    }

    public TripDto findTripById(String name, UUID id){
        TripDto tripDto;
        if(name != null) {
            tripDto = findPurchasedTour(name, id);
            if (tripDto == null || tripDto.getIsPurchared() == null) {
                return toDto(repository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find trip")));
            }

            TripDto favoriteDto = findFavoritesTours(name).stream().filter(t -> t.getId().equals(id))
                    .peek(t -> t.setIsFavorite(true)).findAny().orElse(null);

            if (favoriteDto != null) {
                tripDto.setIsFavorite(true);
            } else {
                return tripDto;
            }
        } else {
           tripDto = toDto(repository.findById(id).orElseThrow());
        }
        return tripDto;
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
                .id(trip.getId())
                .author(trip.getAuthor())
                .country(trip.getCountry())
                .duration(trip.getDuration())
                .image(trip.getImage())
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

    public Set<TripDto> findFavoritesTours(String userName) {
        User user  = userService.findByLogin(userName).orElseThrow(() -> new RuntimeException("User not found"));

        Set<TripDto> returningList = new HashSet<>();
        returningList.addAll(findPurchasedTours(userName).stream().map(this::toDto)
                .peek(t -> t.setIsPurchared(true))
                .collect(Collectors.toList()));
        if(user.getUserDetails() == null || user.getUserDetails().getFavorites() == null){
            return new HashSet<>();
        }
        returningList.addAll(user.getUserDetails().getFavorites().stream()
                .map(this::toDto).peek(t -> t.setIsFavorite(true)).collect(Collectors.toList()));
        return returningList;
    }

    public List<Trip> findPurchasedTours(String userName) {
        User user  = userService.findByLogin(userName).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUserDetails() == null ? new ArrayList<>() : user.getUserDetails().getPurchased() == null
                ? new ArrayList<>() : (List<Trip>) user.getUserDetails().getPurchased();
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

    @Transactional
    public TripDto findPurchasedTour(String name, UUID tourId) {
        if(name == null){
            return null;
        }

        User user  = userService.findByLogin(name).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUserDetails() == null ? new TripDto() : user.getUserDetails().getPurchased() == null
                ? new TripDto() : user.getUserDetails().getPurchased().stream()
                .map(this::toDto)
                .filter(t -> t.getId().equals(tourId)).peek(t -> t.setIsPurchared(true)).findAny().orElse(new TripDto());
    }

    public FilterDto getAllFilters() {
        return FilterDto.builder()
                .country(repository.findAllCountry())
                .region(repository.findAllRegions())
                .durationMin(repository.findMinDuration())
                .durationMax(repository.findMaxDuration())
                .lengthMin(repository.findMinlength())
                .lengthMax(repository.findMaxlength())
                .priceMin(repository.findMinPrice())
                .priceMax(repository.findMaxPrice())
                .build();
    }

    @Transactional
    public void deleteTripById(UUID id) {
       repository.deleteById(id);
    }

    @Transactional
    public void delFromFavorites(String name, UUID tourId) {
        userService.findByLogin(name).orElseThrow().getUserDetails()
                .getFavorites().removeIf(d -> d.getId().equals(tourId));
    }
}
