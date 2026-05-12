package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

/**
 * Маппер для преобразования сущности {@link AdEntity} в DTO и обратно.
 * <p>
 * Использует библиотеку MapStruct для автоматического отображения полей.
 * Обеспечивает корректное формирование ссылок на изображения:
 * <ul>
 *   <li>{@code image} → "/image/ad/{id}"</li>
 *   <li>{@code authorImage} → "/image/user/{id}"</li>
 * </ul>
 * Эти URL используются фронтендом для загрузки изображений через {@link ru.skypro.homework.controller.ImageController}.
 *
 * @see Ad — краткая информация об объявлении (используется в списке)
 * @see ExtendedAd — полная информация об объявлении (одиночный просмотр)
 * @see CreateOrUpdateAd — DTO для создания и редактирования объявления
 * @see AdEntity — JPA-сущность объявления
 */
@Mapper(componentModel = "spring")
public interface AdMapper {

    /**
     * Преобразует сущность объявления в краткий DTO.
     * <p>
     * Используется при отображении списка объявлений.
     *
     * @param adEntity сущность объявления
     * @return объект {@link Ad} с основными полями: ID, заголовок, цена, автор, ссылка на изображение
     */
    @Mapping(source = "pk", target = "pk")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "author.id", target = "author")
    @Mapping(expression = "java(\"/image/ad/\" + adEntity.getPk())", target = "image")
    Ad toDto(AdEntity adEntity);

    /**
     * Преобразует сущность объявления в расширенный DTO.
     * <p>
     * Включает дополнительные поля: описание, контактные данные автора,
     * а также ссылки на аватар автора и изображение объявления.
     *
     * @param adEntity сущность объявления
     * @return объект {@link ExtendedAd} с полной информацией
     */
    @Mapping(source = "pk", target = "pk")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(expression = "java(\"/image/user/\" + adEntity.getAuthor().getId())", target = "authorImage")
    @Mapping(expression = "java(\"/image/ad/\" + adEntity.getPk())", target = "image")
    ExtendedAd toExtendedDto(AdEntity adEntity);

    /**
     * Преобразует DTO создания/обновления в сущность объявления.
     * <p>
     * Используется при создании или редактировании объявления.
     * Некоторые поля игнорируются, так как устанавливаются автоматически:
     * <ul>
     *   <li>{@code pk} — генерируется БД</li>
     *   <li>{@code createdAt} — устанавливается при создании</li>
     *   <li>{@code comments} — связь заполняется отдельно</li>
     *   <li>{@code author} — устанавливается из контекста аутентификации</li>
     * </ul>
     *
     * @param createOrUpdateAd DTO с данными для создания или обновления
     * @return частично заполненная сущность {@link AdEntity}
     */
    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "author", ignore = true)
    AdEntity toEntity(CreateOrUpdateAd createOrUpdateAd);
}