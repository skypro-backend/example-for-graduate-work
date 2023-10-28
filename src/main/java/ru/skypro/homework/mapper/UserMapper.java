package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.model_dto.UpdateUserDto;
import ru.skypro.homework.dto.model_dto.UserDto;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

/**
 * Маппинг сущности пользователя
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

      @Mapping(target = "password", ignore = true)
      @Mapping(target = "image", ignore = true)
      User toUser(UserDto userDto); // конвертация DTO в сущность

      @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
      UserDto toUserDto(User user); // конвертация сущности в DTO

      @Named ("imageToPathString")
      default String imageToPathString(Image image) {
            if (image == null) {
                  return null;
            }
            return "/users/image/" + image.getId();
      }

      @Mapping (target = "id", ignore = true)
      @Mapping(target = "email", source = "username")
      @Mapping(target = "image", ignore = true)
      @Mapping(target = "role", defaultValue = "USER")
      User toUserRegister(Register register); // конвертация получение регистрции от пользователя

      @Mapping(source = "email", target = "username")
      Register toRegister(User user);

      @Mapping (target = "id", ignore = true)
      User toUserUpdateUserDto(UpdateUserDto updateUserDto); // конвертация получение изменений пользователя

      UpdateUserDto toUpdateUserDto(User user);

}
