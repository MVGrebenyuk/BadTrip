package ru.alexsolution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alexsolution.dto.PointDto;
import ru.alexsolution.entity.Point;
import ru.alexsolution.utils.CollectionObjectMapper;

import java.util.List;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MapperTest {

    private final CollectionObjectMapper collectionObjectMapper;

    public MapperTest(@Autowired CollectionObjectMapper collectionObjectMapper) {
        this.collectionObjectMapper = collectionObjectMapper;
    }

    @Test
    void checkCollectionMapper_single(){
        PointDto source = new PointDto(UUID.randomUUID(), "someData", UUID.randomUUID());
        Point target = (Point) collectionObjectMapper.convertValue(source, Point.class);

        Assertions.assertEquals(target.getAuthorId(), source.getAuthorId());
        Assertions.assertEquals(target.getData(), source.getData());
    }

    @Test
    void checkCollectionMapper_collection(){

        PointDto source = new PointDto(UUID.randomUUID(), "someData", UUID.randomUUID());
        PointDto source1 = new PointDto(UUID.randomUUID(), "someData1", UUID.randomUUID());
        List<Point> target = (List<Point>) collectionObjectMapper.convertCollection(List.of(source, source1), Point.class);

        Assertions.assertFalse(target.isEmpty());
        Assertions.assertEquals(target.get(0).getData(), source.getData());
    }

}
