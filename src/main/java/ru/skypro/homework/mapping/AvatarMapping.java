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
    AvatarEntity toModel(Avatar dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "path", target = "path")
    Avatar toDto(AvatarEntity entity);

    List<Avatar> toAvatarDtoList(List<AvatarEntity> entityList);

    List<AvatarEntity> toAvatarEntityList(List<Avatar> dtoList);
}
