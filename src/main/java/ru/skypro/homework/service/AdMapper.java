package ru.skypro.homework.service;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Интерфейс {@code AdMapper} предоставляет методы для преобразования между сущностями
 * рекламных объявлений ({@link AdEntity}) и их представлением в виде Data Transfer Objects (DTO).
 * Обеспечивает удобную конвертацию данных между слоями приложения.
 *
 * <p>Использует библиотеку MapStruct для автоматической генерации кода преобразования.</p>
 *
 * <p>Методы маппера:</p>
 * <ul>
 *     <li>{@code adToDto} - преобразует объект {@link AdEntity} в объект {@link Ad}.</li>
 *     <li>{@code adToDtoList} - преобразует коллекцию объектов {@link AdEntity} в объект {@link Ads}.</li>
 *     <li>{@code DtoToAd} - преобразует объект {@link CreateOrUpdateAd} в объект {@link AdEntity}.</li>
 *     <li>{@code adToExtDto} - преобразует объект {@link AdEntity} в объект {@link ExtendedAd},
 *     включая расширенные данные об авторе.</li>
 * </ul>
 *
 * <p>Каждый метод аннотирован с использованием библиотеки MapStruct, где указаны маппинги
 * между полями источника и цели преобразования.</p>
 *
 * <p>Этот маппер предоставляет централизованный механизм преобразования данных между слоями
 * приложения, обеспечивая чистоту и структурированность кода.</p>
 *
 * @author Michail Z. (GH: HeimTN)
 */
@Mapper(componentModel = "spring")
public interface AdMapper {

    default Ad adToDto(AdEntity ad){
        Ad result = new Ad();
        result.setPk(ad.getId());
        result.setTitle(ad.getTitle());
        result.setAuthor(ad.getAuthor().getId());
        result.setPrice(ad.getPrice());
        result.setImage(ad.getImage());
        return result;
    }

    default Ads adToDtoList(Collection<AdEntity> ads){
        List<Ad> adList = ads.stream().map(this::adToDto).collect(Collectors.toList());
        Ads result = new Ads();
        result.setResults(adList);
        result.setCount(adList.size());
        return result;
    };
    default AdEntity dtoToAd(CreateOrUpdateAd createOrUpdateAd){
        AdEntity result = new AdEntity();
        result.setDescription(createOrUpdateAd.getDescription());
        result.setPrice(createOrUpdateAd.getPrice());
        result.setTitle(createOrUpdateAd.getTitle());
        return result;
    }

    @Mappings({
            @Mapping(source = "id", target = "pk"),
            @Mapping(target = "authorFirstName", expression = "java(ad.getAuthor().getFirstName())"),
            @Mapping(target = "authorLastName", expression = "java(ad.getAuthor().getLastName())"),
            @Mapping(target = "email", expression = "java(ad.getAuthor().getLogin())"),
            @Mapping(target = "phone", expression = "java(ad.getAuthor().getPhone())")
    })
    ExtendedAd adToExtDto(AdEntity ad);
}
