package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "image", expression = "java(userEntity.getImage() != null ? \"/users/image/\" + userEntity.getId() : null)")
    User toDto(UserEntity userEntity);

    UserEntity toEntity(User userDto);
}