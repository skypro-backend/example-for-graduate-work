package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserEntity;

import java.util.Optional;

/**
 * Маппер для преобразования сущности {@link UserEntity} в DTO и обратно.
 * Основные функции:
 * - преобразование сущности пользователя в DTO (User);
 * - преобразование DTO в сущность пользователя для создания/обновления.
 * Также формирует ссылку на аватар пользователя.
 */
@Component
public class UserMapper {

    /**
     * Преобразует сущность пользователя в DTO.
     * Формирует ссылку на аватар в формате /images/{id}.
     * @param entity сущность пользователя
     * @return Optional с DTO или пустой Optional
     */
    public Optional<User> toDto(UserEntity entity) {
            return Optional.ofNullable(entity)
                    .map(e -> {
                        User dto = new User();
                        dto.setId(e.getId());
                        dto.setEmail(e.getEmail());
                        dto.setFirstName(e.getFirstName());
                        dto.setLastName(e.getLastName());
                        dto.setPhone(e.getPhone());
                        dto.setRole(e.getRole());
                        if (e.getAvatar() != null) {
                            dto.setImage("/images/" + e.getAvatar().getId());
                        } else {
                            dto.setImage(null);
                        }
                        return dto;
                    });
    }


    /**
     * Преобразует DTO в сущность пользователя.
     * Используется при создании или обновлении пользователя.
     * @param dto DTO с данными пользователя
     * @return Optional с сущностью или пустой Optional
     */
    public Optional<UserEntity> toEntity(User dto) {
        return Optional.ofNullable(dto)
                .map(d -> {
                    UserEntity entity = new UserEntity();
                    entity.setId(d.getId());
                    entity.setEmail(d.getEmail());
                    entity.setFirstName(d.getFirstName());
                    entity.setLastName(d.getLastName());
                    entity.setPhone(d.getPhone());
                    entity.setRole(d.getRole());
                    return entity;
                });
    }
}
