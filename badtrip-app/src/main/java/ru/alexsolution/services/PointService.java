package ru.alexsolution.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexsolution.dto.PointDto;
import ru.alexsolution.entity.Point;
import ru.alexsolution.repositories.PointRepository;
import ru.alexsolution.utils.CollectionObjectMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointService {

    private final PointRepository repository;
    private final CollectionObjectMapper mapper;
    private final UserService userService;


    public List<PointDto> getAllPoints() {
       return (List<PointDto>) mapper.convertCollection(repository.findAll(), PointDto.class);
    }

    public void savePoint(PointDto point, String name) {
        var user = userService.findByLogin(name);

        if(user.isPresent()){
            point.setAuthorId(user.get().getId());
        }

        point.setPointId(UUID.randomUUID());

        repository.save((Point) mapper.convertValue(point, Point.class));
    }

    @Transactional
    public void updatePoint(PointDto point, String name) {
        UUID userId = point.getAuthorId();

        var pointEntity = repository.findById(point.getPointId())
                .orElseThrow(() -> new EntityNotFoundException());

        enrichData(point, pointEntity, userId);
    }

    private static void enrichData(PointDto point, Point pointEntity, UUID userId) {
        var authorId = pointEntity.getAuthorId();

        if(String.valueOf(authorId).equals(String.valueOf(userId))){
            pointEntity.setData(point.getData());
        } else {
            throw new RuntimeException("Incorrect user for change point");
        }
    }
}
