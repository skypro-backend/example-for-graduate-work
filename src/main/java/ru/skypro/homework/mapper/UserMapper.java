package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

/**
 * Маппер для преобразования сущности {@link UserEntity} в DTO {@link User} и обратно.
 * <p>
 * Использует MapStruct для автоматического отображения полей. Особое внимание уделяется:
 * <ul>
 *   <li>Формированию ссылки на аватар пользователя: {@code /image/user/{id}}</li>
 *   <li>Игнорированию чувствительных или управляемых сервером полей при создании сущности</li>
 * </ul>
 *
 * @see User — DTO для передачи данных пользователя фронтенду (без пароля)
 * @see UserEntity — JPA-сущность пользователя с полной информацией, включая пароль и связи
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Преобразует сущность пользователя в DTO для передачи клиенту.
     * <p>
     * Особенности:
     * <ul>
     *   <li>{@code image} формируется как "/image/user/{id}" — используется {@link ru.skypro.homework.controller.ImageController}</li>
     *   <li>Поле {@code password} не включается в DTO (не передаётся фронтенду)</li>
     *   <li>Связанные объекты (объявления, комментарии) не включаются — загружаются отдельно</li>
     * </ul>
     *
     * @param userEntity сущность пользователя из базы данных
     * @return объект {@link User} с данными для отображения на фронтенде
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "role", target = "role")
    @Mapping(expression = "java(\"/image/user/\" + userEntity.getId())", target = "image")
    User toDto(UserEntity userEntity);

    /**
     * Преобразует DTO пользователя в сущность для сохранения в базе.
     * <p>
     * Поля, устанавливаемые сервером или не передаваемые клиентом, игнорируются:
     * <ul>
     *   <li>{@code password} — устанавливается при регистрации или изменении</li>
     *   <li>{@code ads} — список объявлений, связанный с пользователем</li>
     *   <li>{@code comments} — список комментариев, оставленных пользователем</li>
     * </ul>
     * Этот метод используется при обновлении профиля, где не требуется передача всех данных.
     *
     * @param user DTO с обновляемыми полями (имя, фамилия, телефон)
     * @return частично заполненная сущность {@link UserEntity}
     */
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserEntity toEntity(User user);
}