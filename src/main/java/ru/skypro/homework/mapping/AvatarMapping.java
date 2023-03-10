package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Avatar;
import ru.skypro.homework.entity.AvatarEntity;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AvatarMapping {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "path", target = "path")
    AvatarEntity toEntity(Avatar dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "path", target = "path")
    Avatar toDto(AvatarEntity entity);

    List<Avatar> toDtoList(List<AvatarEntity> entityList);

    List<AvatarEntity> toEntityList(List<Avatar> dtoList);
}
