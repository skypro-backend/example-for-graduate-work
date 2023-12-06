package ru.skypro.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;

@Component
@RequiredArgsConstructor
public class ImageMapper {

    public ImageDTO mapToDTO(Image image) {
        return new ImageDTO(
                "/image/" + image.getId()
        );
    }

}
