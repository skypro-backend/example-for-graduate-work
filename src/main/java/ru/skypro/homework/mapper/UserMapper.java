package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.MyDatabaseUser;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserMapper {

    @Mapping(target = "email", source = "username")
    @Mapping(target = "image", expression = "java(getImageUrl(userEntity.getImage()))")
    public abstract User userEntityToUser(UserEntity userEntity);

    public abstract UpdateUser userEntityToUpdateUser(UserEntity userEntity);

    public abstract UserEntity registerToUserEntity(Register register);

    public abstract UserEntity myDatabaseUserToUserEntity(MyDatabaseUser myDatabaseUser);

    public abstract MyDatabaseUser userEntityToMyDatabaseUser(UserEntity userEntity);

    public abstract void updateUserToUserEntity(UpdateUser updateUser, @MappingTarget UserEntity userEntity);

    protected String getImageUrl(Image image) {
        if (image == null) {
            return null;
        }
        return "/images/" + image.getId();
    }
}
