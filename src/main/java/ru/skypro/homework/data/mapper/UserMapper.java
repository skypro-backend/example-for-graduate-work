package ru.skypro.homework.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.data.dto.user.UpdateUserProfileRequest;
import ru.skypro.homework.data.dto.user.UserDto;
import ru.skypro.homework.data.entity.AccountEntity;
import ru.skypro.homework.data.entity.ProfileEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {

    @Mapping(target = "id", source = "account.id")
    @Mapping(target = "email", source = "account.email")
    @Mapping(target = "role", source = "account.role")
    @Mapping(target = "firstName", source = "profile.firstName")
    @Mapping(target = "lastName", source = "profile.lastName")
    @Mapping(target = "phone", source = "profile.phone")
    @Mapping(target = "image", source = "profile.avatarS3Key")
    UserDto toUserDto(AccountEntity account, ProfileEntity profile);

    default UserDto toUserDto(AccountEntity account) {
        return toUserDto(account, account.getProfile());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "avatarS3Key", ignore = true)
    void updateProfileFromDto(UpdateUserProfileRequest request, @MappingTarget ProfileEntity entity);


}
