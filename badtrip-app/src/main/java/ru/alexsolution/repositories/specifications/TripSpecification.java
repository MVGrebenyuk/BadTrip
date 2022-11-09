package ru.alexsolution.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.alexsolution.dto.InputFilterDto;
import ru.alexsolution.entity.Trip;

import java.math.BigDecimal;

public class TripSpecification {
    public static Specification<Trip> priceLessOrEqualsThan(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Trip> priceGreaterOrEqualsThan(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Trip> lengtheLessOrEqualsThan(BigDecimal length) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("length"), length);
    }

    public static Specification<Trip> lengthGreaterOrEqualsThan(BigDecimal length) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("length"), length);
    }

    public static Specification<Trip> durationLessOrEqualsThan(Integer duration) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("duration"), duration);
    }

    public static Specification<Trip> durationGreaterOrEqualsThan(Integer duration) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("duration"), duration);
    }

    public static Specification<Trip> countryLike(String country) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("country"), String.format("%%%s%%", country));
    }

    public static Specification<Trip> regionLike(String region) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("region"), String.format("%%%s%%", region));
    }

    public static Specification<Trip> levelLessOrEqualsThan(Integer level) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("level"), level);
    }

    public static Specification<Trip> levelGreaterOrEqualsThan(Integer level) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("level"), level);
    }

    public static Specification<Trip> levelIs(Integer level) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("level"), level);
    }

    public static Specification<Trip> fillSpecification(InputFilterDto filter) {

        Specification<Trip> specification = Specification.where(null);
        if(filter.getCity() != null){
            //ToDo
        }
        if(filter.getCountry() != null){
            specification = TripSpecification.countryLike(filter.getCountry());
        }
        if(filter.getRegion() != null){
            specification = TripSpecification.regionLike(filter.getRegion());
        }
        if(filter.getDurationMin() != null){
            specification = TripSpecification.durationGreaterOrEqualsThan(filter.getDurationMin());
        }
        if(filter.getDurationMax() != null){
            specification = TripSpecification.durationLessOrEqualsThan(filter.getDurationMax());
        }
        if(filter.getPriceMax() != null){
            specification = TripSpecification.priceLessOrEqualsThan(filter.getPriceMax());
        }
        if(filter.getPriceMin() != null){
            specification = TripSpecification.priceGreaterOrEqualsThan(filter.getPriceMin());
        }
        if(filter.getLengthMax() != null){
            specification = TripSpecification.lengtheLessOrEqualsThan(filter.getLengthMax());
        }
        if(filter.getLengthMin() != null){
            specification = TripSpecification.lengthGreaterOrEqualsThan(filter.getLengthMin());
        }
        if(filter.getLevel() != null){
            specification = TripSpecification.levelIs(filter.getLevel());
        }
        if(filter.getCity() != null){
            //ToDo
        }

        return specification;
    }


}
