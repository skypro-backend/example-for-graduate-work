package ru.skypro.homework.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.auth.LoginDto;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.roles.Role;
import ru.skypro.homework.entity.roles.Roles;
import ru.skypro.homework.entity.users.User;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role", target = "roles", qualifiedByName = "mapRoleToSetOfRoles")
    User toEntity(RegisterDto registerDto, @Context CustomUserMapper customUserMapper);

    @Named("mapRoleToSetOfRoles")
    default Set<Roles> mapRoleToSetOfRoles(Role role, @Context CustomUserMapper customUserMapper) {
        return customUserMapper.mapRoleToSetOfRoles(role);
    }

    User toEntity(UserDto userDto);

    User toEntity(UpdateUserDto updateUserDto);

    User toEntity(LoginDto loginDto);

    User toEntity(NewPasswordDto newPasswordDto);


    UserDto toUserDto(User user);

    UpdateUserDto toUpdateUserDto(User user);

    LoginDto toLoginDto(User user);

    NewPasswordDto toNewPasswordDto(User user);

    RegisterDto toRegisterDto(User user);

    default Integer toInteger(User user) {
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    void updateUser(UpdateUserDto updateUserDto, @MappingTarget User user);

}