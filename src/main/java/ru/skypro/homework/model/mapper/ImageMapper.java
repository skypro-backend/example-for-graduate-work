package ru.skypro.homework.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.model.dto.ImageDto;
import ru.skypro.homework.model.entity.Image;

@Mapper
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto imageToImageDto(Image image);

    Image imageDtoToImage(ImageDto imageDto);
}
