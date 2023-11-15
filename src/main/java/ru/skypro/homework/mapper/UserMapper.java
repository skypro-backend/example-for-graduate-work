package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.model.User;
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", ignore = true) // Игнорируем id, так как он будет присвоен автоматически
    @Mapping(target = "email", source = "username")
    User registerDTOToUser (RegisterDTO registerDTO);


}
