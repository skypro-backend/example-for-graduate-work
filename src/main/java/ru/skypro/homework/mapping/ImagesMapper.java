package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AvatarDto;
import ru.skypro.homework.model.Images;

@Mapper
public interface ImagesMapper {

    ImagesMapper INSTANCE = Mappers.getMapper(ImagesMapper.class);

    AvatarDto imagesToAvatarDto(Images image);
}
