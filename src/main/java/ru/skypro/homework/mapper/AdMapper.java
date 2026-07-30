package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

/**
 * Преобразует сущность объявления в DTO и обратно.
 */
@Component
public class AdMapper {

    /**
     * Создаёт сущность объявления.
     *
     * @param request данные объявления
     * @param author  автор объявления
     * @return новая сущность объявления
     */
    public AdEntity toEntity(
            CreateOrUpdateAd request,
            UserEntity author
    ) {
        AdEntity entity = new AdEntity();

        entity.setTitle(request.getTitle());
        entity.setPrice(request.getPrice());
        entity.setDescription(request.getDescription());
        entity.setAuthor(author);

        return entity;
    }

    /**
     * Преобразует объявление в краткий DTO.
     *
     * @param entity сущность объявления
     * @return краткая информация об объявлении
     */
    public Ad toDto(AdEntity entity) {
        return new Ad(
                entity.getAuthor().getId().intValue(),
                buildImageUrl(entity),
                entity.getId().intValue(),
                entity.getPrice(),
                entity.getTitle()
        );
    }

    /**
     * Преобразует объявление в полный DTO.
     *
     * @param entity сущность объявления
     * @return полная информация об объявлении
     */
    public ExtendedAd toExtendedDto(AdEntity entity) {
        UserEntity author = entity.getAuthor();

        return new ExtendedAd(
                entity.getId().intValue(),
                author.getFirstName(),
                author.getLastName(),
                entity.getDescription(),
                author.getUsername(),
                buildImageUrl(entity),
                author.getPhone(),
                entity.getPrice(),
                entity.getTitle()
        );
    }

    /**
     * Обновляет данные объявления.
     *
     * @param entity  изменяемое объявление
     * @param request новые данные
     */
    public void updateEntity(
            AdEntity entity,
            CreateOrUpdateAd request
    ) {
        entity.setTitle(request.getTitle());
        entity.setPrice(request.getPrice());
        entity.setDescription(request.getDescription());
    }

    private String buildImageUrl(AdEntity entity) {
        if (entity.getImage() == null || entity.getId() == null) {
            return null;
        }

        return "/ads/" + entity.getId() + "/image";
    }
}
