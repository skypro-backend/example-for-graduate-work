package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.model_dto.UpdateUserDto;
import ru.skypro.homework.dto.model_dto.UserDto;
import ru.skypro.homework.model.User;


/**
 * Маппинг сущности пользователя
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
      UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

      @Mapping(target = "password", ignore = true)
      @Mapping(target = "role", ignore = true)
      User toUser(UserDto userDto); // конвертация DTO в сущность
      UserDto toUserDto(User user); // конвертация сущности в DTO
      @Mapping (target = "id", ignore = true)
      @Mapping(target = "email", source = "username")
      @Mapping(target = "password", source = "password")
      @Mapping(target = "image", ignore = true)
      @Mapping(target = "role", defaultValue = "USER")
      User toUser(Register register); // конвертация получение регистрции от пользователя
      @Mapping(source = "email", target = "username")
      Register toRegister(User user);
      @Mapping (target = "id", ignore = true)
      User toUser(UpdateUserDto updateUserDto); // конвертация получение изменений пользователя

}
