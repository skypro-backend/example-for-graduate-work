package ru.skypro.homework.mapper;


import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.UserEntity;

/**
 * маппер для {@link UserEntity} готовый dto {@link UserDTO}
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "regDate", source = "regDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
  @Mapping(target = "adEntities", ignore = true)
  @Mapping(target = "commentEntities", ignore = true)
  UserEntity toEntity(UserDTO userDto);

  @Mapping(target = "regDate", source = "regDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
  UserDTO toDTO(UserEntity userEntity);

  Collection<UserEntity> toEntityList(Collection<UserDTO> userDTOS);

  Collection<UserDTO> toDTOList(Collection<UserEntity> userEntities);
}
