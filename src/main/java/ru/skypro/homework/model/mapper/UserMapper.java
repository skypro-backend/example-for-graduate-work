package ru.skypro.homework.model.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.model.dto.RegisterReqDto;
import ru.skypro.homework.model.dto.UserDto;
import ru.skypro.homework.model.entity.ProfileUser;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "avatar.id", source = "avatar")
    @Mapping(target = "id", source = "id")
    ProfileUser userDtoToUser(UserDto userDto);
    @Mapping(source = "avatar.id", target = "avatar")
    @Mapping(target = "id", source = "id")
    UserDto userToUserDto(ProfileUser profileUser);

    @BeanMapping (nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "avatar.id", source = "avatar")
    @Mapping(target = "id", source = "id")
    void partialUpdate(UserDto userDto, @MappingTarget ProfileUser profileUser);


    @Mapping(source = "roleEnum", target = "role")
    @Mapping(source = "username", target = "email")
    ProfileUser registerReqDtoToUser(RegisterReqDto registerReqDto);

}