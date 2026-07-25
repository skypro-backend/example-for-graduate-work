package com.skypro.mapper;

import com.skypro.dto.Ad;
import com.skypro.dto.CreateOrUpdateAd;
import com.skypro.dto.ExtendedAd;
import com.skypro.entity.AdEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct-маппер для преобразования между сущностью {@link AdEntity}
 * и DTO {@link Ad}, {@link ExtendedAd}, {@link CreateOrUpdateAd}.
 * <p>
 * Используется в сервисном слое для преобразования данных перед отправкой
 * клиенту и перед сохранением в базу данных.
 * </p> */
@Mapper(componentModel = "spring")
public interface AdMapper {

    /**
     * Преобразует {@link AdEntity} в {@link Ad} (краткая версия для списков).
     * <p>
     * Маппинг полей:
     * <ul>
     *   <li>{@code id} → {@code pk} (для совместимости с фронтендом)</li>
     *   <li>{@code author.id} → {@code author} (ID автора)</li>
     *   <li>{@code image} → формируется путь /images/ads/ + имя файла</li>
     * </ul>
     * </p>
     *
     * @param adEntity сущность объявления
     * @return DTO {@link Ad}
     */
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "image", expression = "java(\"/images/ads/\" + adEntity.getImage())")
    Ad toAd(AdEntity adEntity);

    /**
     * Преобразует {@link CreateOrUpdateAd} в {@link AdEntity}.
     * <p>
     * Поля {@code id}, {@code author}, {@code image}, {@code createdAt} и {@code comments}
     * игнорируются, так как они либо генерируются автоматически, либо устанавливаются
     * сервисным слоем.
     * </p>
     *
     * @param createOrUpdateAd DTO с данными для создания/обновления
     * @return сущность {@link AdEntity}
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    AdEntity toEntity(CreateOrUpdateAd createOrUpdateAd);

    /**
     * Преобразует {@link AdEntity} в {@link ExtendedAd} (детальная версия).
     * <p>
     * Маппинг полей:
     * <ul>
     *   <li>{@code id} → {@code pk} (для совместимости с фронтендом)</li>
     *   <li>{@code author.firstName} → {@code authorFirstName}</li>
     *   <li>{@code author.lastName} → {@code authorLastName}</li>
     *   <li>{@code author.username} → {@code email}</li>
     *   <li>{@code author.phone} → {@code phone}</li>
     *   <li>{@code image} → формируется путь /images/ads/ + имя файла</li>
     * </ul>
     * </p>
     *
     * @param adEntity сущность объявления
     * @return DTO {@link ExtendedAd}
     */
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.username", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(target = "image", expression = "java(\"/images/ads/\" + adEntity.getImage())")
    ExtendedAd toExtendedAd(AdEntity adEntity);
}
