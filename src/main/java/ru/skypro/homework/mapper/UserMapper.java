package ru.skypro.homework.mapper;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.RoleEntity;
import ru.skypro.homework.entity.UserEntity;

/**
 * Маппер для преобразования между UserEntity и User DTO.
 * Включает преобразование роли и восстановление всех основных данных пользователя.
 */
@Component
public class UserMapper {

    public User toDto(UserEntity entity) {
        if (entity == null) return null;

        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phone(entity.getPhone())
                .role(entity.getRole().getName())
                .image(entity.getImage())
                .build();
    }
    public UserEntity toEntity(User dto) {
        if (dto == null) return null;

        UserEntity entity = new UserEntity();
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setImage(dto.getImage());

        if (dto.getRole() != null) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(dto.getRole());
            entity.setRole(roleEntity);
        }

        return entity;
    }

}
