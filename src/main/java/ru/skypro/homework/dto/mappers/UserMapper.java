package ru.skypro.homework.dto.mappers;

import org.mapstruct.*;
import ru.skypro.homework.dto.auth.LoginDto;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.users.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    User toEntity(UpdateUserDto updateUserDto);

    User toEntity(LoginDto loginDto);

    @Mapping(target = "newPasswod", expression = "java")
    User toEntity(NewPasswordDto newPasswordDto);

    @Mapping(target = "password", expression = "java(encodePassword(registerDto))")
    User toEntity(RegisterDto registerDto);

//    @AfterMapping
//    @Named("encodePassword")
//    default void encodePassword(@MappingTarget User user, RegisterDto registerDto) {
//        if (registerDto != null) {
//            String password = registerDto.getPassword();
//            Integer hashCode = password.hashCode();
//            user.setPassword(hashCode);
//        }
//    }

    default Integer encodePassword(RegisterDto registerDto) {
        if (registerDto != null) {
            String password = registerDto.getPassword();
            return password.hashCode();
        }
        return null;
    }

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
}