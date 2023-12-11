package ru.skypro.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.model.Image;

@Component
@RequiredArgsConstructor
public class ImageMapper {

    /**
     * Mapping entity to DTO
     * @param image
     * @return ImageDTO
     */
    public ImageDTO mapToDTO(Image image) {
        return new ImageDTO(
                "/image/" + image.getId()
        );
    }

}
