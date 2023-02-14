package ru.skypro.homework.mapper;


import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.UserEntity;

import java.util.Collection;

/**
 * маппер для {@link UserEntity} готовый dto {@link UserDTO}
 */

//@Mapper(componentModel = "spring")
public interface UserMapper {

  UserEntity toEntity(UserDTO userDto);

  UserDTO toDTO(UserEntity userEntity);

  Collection<UserEntity> toEntityList(Collection<UserDTO> userDTOS);

  Collection<UserDTO> toDTOList(Collection<UserEntity> userEntities);
}
