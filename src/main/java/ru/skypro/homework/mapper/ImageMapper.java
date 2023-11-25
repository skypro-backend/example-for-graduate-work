package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.model.Image;

@Component
public class ImageMapper {
    public ImageDTO mapToDTO(Image image) {
        return new ImageDTO(
                image.getId(),
                image.getFilePath(),
                image.getFileSize(),
                image.getMediaType(),
                image.getData()
        );
    }

    public Image mapToEntity(ImageDTO imageDTO) {
        return new ImageDTO(
                imageDTO.getId(),
                imageDTO.getFilePath(),
                imageDTO.getFileSize(),
                imageDTO.getMediaType(),
                imageDTO.getData()
        );
    }
}
