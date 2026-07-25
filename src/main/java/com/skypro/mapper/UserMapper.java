package com.skypro.mapper;

import com.skypro.dto.RegisterReq;
import com.skypro.dto.UpdateUser;
import com.skypro.dto.User;
import com.skypro.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct-маппер для преобразования между сущностью {@link UserEntity}
 * и DTO {@link User}, {@link RegisterReq}, {@link UpdateUser}.
 * <p>
 * Используется в сервисном слое для регистрации, получения и обновления
 * данных пользователей.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Преобразует {@link RegisterReq} в {@link UserEntity}.
     * <p>
     * Поля {@code id}, {@code image}, {@code ads} и {@code comments}
     * игнорируются, так как они либо генерируются автоматически,
     * либо устанавливаются сервисным слоем.
     * </p>
     *
     * @param registerReq DTO с данными для регистрации
     * @return сущность {@link UserEntity}
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserEntity toEntity(RegisterReq registerReq);

    /**
     * Преобразует {@link UserEntity} в {@link User}.
     * <p>
     * Маппинг полей:
     * <ul>
     *   <li>{@code image} → формируется путь /images/users/ + имя файла,
     *       если {@code image} не равно {@code null}</li>
     * </ul>
     * </p>
     *
     * @param userEntity сущность пользователя
     * @return DTO {@link User}
     */
    @Mapping(target = "image", expression = "java(userEntity.getImage() != null ? \"/images/users/\" + userEntity.getImage() : null)")
    User toUser(UserEntity userEntity);

    /**
     * Преобразует {@link UserEntity} в {@link UpdateUser}.
     * <p>
     * Используется для обновления данных пользователя.
     * Маппинг происходит автоматически по совпадающим полям.
     * </p>
     *
     * @param userEntity сущность пользователя
     * @return DTO {@link UpdateUser}
     */
    UpdateUser toUpdateUser(UserEntity userEntity);
}
