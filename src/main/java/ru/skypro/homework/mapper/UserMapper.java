package ru.skypro.homework.mapper;


import java.util.Collection;
import org.mapstruct.Mapper;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.UserEntity;

/**
 * маппер для {@link UserEntity} готовый dto {@link UserDTO}
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserEntity toEntity(UserDTO userDto);

  UserDTO toDTO(UserEntity userEntity);

  Collection<UserEntity> toEntityList(Collection<AdsDTO> userDTOS);

  Collection<UserDTO> toDTOList(Collection<UserEntity> userEntities);
}
