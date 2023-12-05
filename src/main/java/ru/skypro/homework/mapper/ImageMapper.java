package ru.skypro.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;

@Component
@RequiredArgsConstructor
public class ImageMapper {

    private final ImageRepository imageRepository;

    public ImageDTO mapToDTO(Image image) {
        return new ImageDTO(
                "/image/" + image.getId()
        );
    }

    public Image mapToEntity(ImageDTO imageDTO) {
        Integer id = Integer.parseInt(String.valueOf(imageDTO.getUrl().charAt(imageDTO.getUrl().length() - 1)));
        return new Image(
                id,
                imageRepository.findById(id).get().getMediaType(),
                imageRepository.findById(id).get().getData()
        );
    }
}
