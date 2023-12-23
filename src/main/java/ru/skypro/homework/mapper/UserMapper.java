package ru.skypro.homework.mapper;
import lombok.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
@Component
public class UserMapper {
    public User userEntityToUser(UserEntity userEntity) {
        if (userEntity == null) {
            throw new NullPointerException("Ошибка маппера при создании User! UserEntity == null!");
        }
        return User.builder()
                .id(userEntity.getId())
                .email(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phone(userEntity.getPhone())
                .role(userEntity.getRole())
                .image(userEntity.getImageEntity().getFilePath())
                .build();
    }

    public UserEntity UserToAdUserEntity(Register dto) {
        if (dto == null) {
            throw new NullPointerException(" Ошибка маппера при создании UserEntity! Register == null! ");
        }
        return UserEntity.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .role(dto.getRole())
                .build();
    }

    public List<User> adEntityListToAdList(List<UserEntity> userEntityList) {
        if (userEntityList == null) {
            throw new NullPointerException("Ошибка маппера при создании List<User>! List<UserEntity> == null!");
        }
        return userEntityList.stream()
                .map(this::userEntityToUser)
                .collect(Collectors.toList());
    }
}
