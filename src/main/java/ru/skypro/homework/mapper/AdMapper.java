package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;

import java.util.Optional;

/**
 * Маппер для преобразования сущности {@link AdEntity} в DTO и обратно.
 * Основные функции:
 * - преобразование сущности объявления в краткое DTO (Ad) для списков;
 * - преобразование сущности в расширенное DTO (ExtendedAd) для детального просмотра;
 * - преобразование DTO (CreateOrUpdateAd) в сущность для создания/обновления.
 */
@Component
public class AdMapper {

    /**
     * Преобразует сущность объявления в краткое DTO.
     * Формирует ссылку на картинку в формате /images/{id}.
     * @param entity сущность объявления
     * @return Optional с DTO или пустой Optional
     */
    public Optional<Ad> toAdDto(AdEntity entity) {
        return Optional.ofNullable(entity)
                .map(e -> {
                    Ad dto = new Ad();
                    dto.setPk(e.getId());
                    dto.setTitle(e.getTitle());
                    dto.setPrice(e.getPrice());
                    if (e.getImage() != null) {
                        dto.setImage("/images/" + e.getImage().getId());
                    } else {
                        dto.setImage(null);
                    }
                    if (e.getAuthor() != null) {
                        dto.setAuthor(e.getAuthor().getId());
                    }
                    return dto;
                });
    }

    /**
     * Преобразует сущность объявления в расширенное DTO.
     * Включает полную информацию об объявлении и данные автора.
     * @param entity сущность объявления
     * @return Optional с расширенным DTO или пустой Optional
     */
    public Optional<ExtendedAd> toExtendedAdDto(AdEntity entity) {
        return Optional.ofNullable(entity)
                .map(e -> {
                    ExtendedAd dto = new ExtendedAd();
                    dto.setPk(e.getId());
                    dto.setTitle(e.getTitle());
                    dto.setPrice(e.getPrice());
                    dto.setDescription(e.getDescription());
                    if (e.getImage() != null) {
                        dto.setImage("/images/" + e.getImage().getId());
                    } else {
                        dto.setImage(null);
                    }
                    if (e.getAuthor() != null) {
                        dto.setAuthorFirstName(e.getAuthor().getFirstName());
                        dto.setAuthorLastName(e.getAuthor().getLastName());
                        dto.setEmail(e.getAuthor().getEmail());
                        dto.setPhone(e.getAuthor().getPhone());
                    }
                    return dto;
                });
    }


    /**
     * Преобразует DTO в сущность объявления.
     * Используется при создании или обновлении объявления.
     * @param dto DTO с данными для создания/обновления
     * @return Optional с сущностью или пустой Optional
     */
    public Optional<AdEntity> toEntity(CreateOrUpdateAd dto) {
        return Optional.ofNullable(dto)
                .map(d -> {
                    AdEntity entity = new AdEntity();
                    entity.setTitle(d.getTitle());
                    entity.setPrice(d.getPrice());
                    entity.setDescription(d.getDescription());
                    return entity;
                });
    }
}
