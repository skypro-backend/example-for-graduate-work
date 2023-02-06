package ru.skypro.homework.model.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.model.dto.LoginReqDto;
import ru.skypro.homework.model.dto.RegisterReqDto;
import ru.skypro.homework.model.dto.UserDto;
import ru.skypro.homework.model.entity.ProfileUser;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "avatar.id", source = "avatar")
    @Mapping(target = "id", source = "id")
    ProfileUser userToUserDto(UserDto userDto);
    @Mapping(source = "avatar.id", target = "avatar")
    @Mapping(target = "id", source = "id")
    UserDto UserDtoToUser(ProfileUser profileUser);

    @BeanMapping (nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void partialUpdate(UserDto userDto, @MappingTarget ProfileUser profileUser);

    @Mapping(source = "roleEnum", target = "role")
    ProfileUser registerReqDtoToUser(RegisterReqDto registerReqDto);
    @Mapping(target = "username", source = "email")
    LoginReqDto UserToLoginRegDto (ProfileUser user);

}