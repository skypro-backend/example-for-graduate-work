package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

/**
 * Маппер для преобразования между сущностью объявления и DTO.
 * Использует MapStruct для автоматической генерации кода маппинга.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {

    /**
     * Преобразует сущность объявления в DTO для списка объявлений.
     *
     * @param adEntity сущность объявления
     * @return DTO объявления
     */
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "image", target = "image")
    Ad toDto(AdEntity adEntity);

    /**
     * Преобразует DTO создания объявления в сущность.
     *
     * @param createOrUpdateAd DTO с данными объявления
     * @return сущность объявления
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "comments", ignore = true)
    AdEntity toEntity(CreateOrUpdateAd createOrUpdateAd);

    /**
     * Обновляет существующую сущность объявления данными из DTO.
     *
     * @param createOrUpdateAd DTO с обновленными данными
     * @param adEntity сущность для обновления
     * @return обновленная сущность
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "comments", ignore = true)
    AdEntity updateAdEntity(CreateOrUpdateAd createOrUpdateAd, @MappingTarget AdEntity adEntity);

    /**
     * Преобразует сущность объявления в расширенный DTO для детального просмотра.
     *
     * @param adEntity сущность объявления
     * @return расширенный DTO объявления
     */
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.username", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(source = "image", target = "image")
    ExtendedAd toExtendedDto(AdEntity adEntity);

    /**
     * Создает сущность объявления с установленным автором и путем к изображению.
     *
     * @param createOrUpdateAd DTO с данными объявления
     * @param author сущность автора
     * @param imagePath путь к изображению
     * @return созданная сущность объявления
     */
    default AdEntity createAdEntity(CreateOrUpdateAd createOrUpdateAd, UserEntity author, String imagePath) {
        AdEntity adEntity = toEntity(createOrUpdateAd);
        adEntity.setAuthor(author);
        adEntity.setImage(imagePath);
        return adEntity;
    }
}