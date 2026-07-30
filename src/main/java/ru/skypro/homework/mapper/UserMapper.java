package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

/**
 * Преобразует сущность пользователя в DTO и обратно.
 */
@Component
public class UserMapper {

    /**
     * Создаёт сущность пользователя из регистрационных данных.
     *
     * @param register регистрационные данные
     * @return новая сущность пользователя
     */
    public UserEntity toEntity(Register register) {
        UserEntity entity = new UserEntity();

        entity.setUsername(register.getUsername());
        entity.setPassword(register.getPassword());
        entity.setFirstName(register.getFirstName());
        entity.setLastName(register.getLastName());
        entity.setPhone(register.getPhone());
        entity.setRole(register.getRole());

        return entity;
    }

    /**
     * Преобразует сущность пользователя в DTO.
     *
     * @param entity сущность пользователя
     * @return информация о пользователе
     */
    public User toDto(UserEntity entity) {
        return new User(
                entity.getId().intValue(),
                entity.getUsername(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhone(),
                entity.getRole(),
                buildImageUrl(entity)
        );
    }

    /**
     * Обновляет изменяемые данные пользователя.
     *
     * @param entity     изменяемая сущность
     * @param updateUser новые данные профиля
     */
    public void updateEntity(
            UserEntity entity,
            UpdateUser updateUser
    ) {
        entity.setFirstName(updateUser.getFirstName());
        entity.setLastName(updateUser.getLastName());
        entity.setPhone(updateUser.getPhone());
    }

    /**
     * Возвращает DTO изменяемых данных пользователя.
     *
     * @param entity сущность пользователя
     * @return данные профиля
     */
    public UpdateUser toUpdateUser(UserEntity entity) {
        return new UpdateUser(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhone()
        );
    }

    private String buildImageUrl(UserEntity entity) {
        if (entity.getImage() == null || entity.getId() == null) {
            return null;
        }

        return "/users/" + entity.getId() + "/image";
    }
}
