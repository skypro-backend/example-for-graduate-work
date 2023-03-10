package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Poster;
import ru.skypro.homework.entity.PosterEntity;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PosterMapping {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "adsId")
    @Mapping(source = "path", target = "path")
    PosterEntity toModel(Poster dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "adsId", target = "userId")
    @Mapping(source = "path", target = "path")
    Poster toDto(PosterEntity entity);

    List<Poster> toPosterDtoList(List<PosterEntity> entityList);

    List<PosterEntity> toPosterEntityList(List<Poster> dtoList);
}
