package ru.skypro.homework.transformer;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.MyDatabaseUserDetails;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Component
public class UserTransformer {

    public User userEntitytoUser (UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getUsername());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhone(userEntity.getPhone());
        user.setRole(userEntity.getRole());
        user.setImage(userEntity.getImage());
        return user;
    }

    public UserEntity userToUserEntity(User user) {
        if (user == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userEntity.setRole(user.getRole());
        userEntity.setImage(user.getImage());
        return userEntity;
    }

    public UpdateUser userEntityToUpdateUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UpdateUser updateUser = new UpdateUser();
        updateUser.setFirstName(userEntity.getFirstName());
        updateUser.setLastName(userEntity.getLastName());
        updateUser.setPhone(userEntity.getPhone());
        return updateUser;
    }

    public UserEntity fromUpdateUserToUserEntity (UpdateUser updateUser) {
        if (updateUser == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPassword(updateUser.getPhone());
        return userEntity;
    }

    public UserEntity fromRegisterToUserEntity(Register register) {
        if (register == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(register.getUsername());
        userEntity.setPassword(register.getPassword());
        userEntity.setFirstName(register.getFirstName());
        userEntity.setLastName(register.getLastName());
        userEntity.setPhone(register.getPhone());
        userEntity.setRole(register.getRole());
        return userEntity;
    }

    public UserEntity fromMyDatabaseUserDetailsToUserEntity(MyDatabaseUserDetails myDatabaseUserDetails) {
        return myDatabaseUserDetails.getUserEntity();
    }
}
