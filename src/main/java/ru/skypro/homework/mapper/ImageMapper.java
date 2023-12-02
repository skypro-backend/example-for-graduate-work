package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ImageDTO;

@Component
public class ImageMapper {
    public ImageDTO mapToDTO(ru.skypro.homework.model.Image image) {
        return new ImageDTO(
                image.getId(),
                image.getFilePath(),
                image.getFileSize(),
                image.getMediaType(),
                image.getData(),
                image.getLink()
        );
    }

    public ru.skypro.homework.model.Image mapToEntity(ImageDTO imageDTO) {
        return new ru.skypro.homework.model.Image(
                imageDTO.getId(),
                imageDTO.getFilePath(),
                imageDTO.getFileSize(),
                imageDTO.getMediaType(),
                imageDTO.getData(),
                imageDTO.getLink()
        );
    }
}
