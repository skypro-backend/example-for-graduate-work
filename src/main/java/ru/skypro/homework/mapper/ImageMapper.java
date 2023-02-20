package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.ImageEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper {

    List<String> toListPathString(List<ImageEntity> imageEntities) {
        return imageEntities.stream()
                .map(ImageEntity::getPath)
                .collect(Collectors.toList());
    }
}
