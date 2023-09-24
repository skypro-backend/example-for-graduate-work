package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.model_dto.UpdateUserDto;
import ru.skypro.homework.dto.model_dto.UserDto;
import ru.skypro.homework.model.User;

import java.awt.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

      UpdateUserDto toUpdateUserDto(User entity);
      User updateUserDtoToEntity(UpdateUserDto dto);

      @Mapping (target = "id", ignore = true)
      @Mapping(target = "email", source = "username")
      @Mapping(target = "password", source = "password")
      @Mapping(target = "image", ignore = true)
      @Mapping(target = "role", defaultValue = "USER")
      User toEntity(Register dto);

      @Mapping(target = "password", ignore = true)
      @Mapping(target = "image", ignore = true)
      @Mapping(target = "role", ignore = true)
      User toEntity(UserDto dto);

      @Mapping(target = "image", qualifiedByName = "imageMapping")
      UserDto toDto(User entity);

      @Named("imageMapping")
      default String imageMapping(String image) {
            if (image == null) {
                  return "";
            }
            return "/users/image/" ;

      }

}
