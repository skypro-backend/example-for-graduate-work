package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

/**
 * Маппинг сущности объявления
 */
@Mapper (componentModel = "spring")
public interface AdMapper {
      @Mapping(target = "id", source = "pk")
      @Mapping(target = "author.id", source = "author")
      Ad toAd(AdDto adDto); // конвертация DTO в сущность

      @Mapping(target = "pk", source = "id")
      @Mapping(target = "author", source = "author.id")
      AdDto AdDto(Ad ad); // конвертация сущности в DTO

      @Mapping(target = "id", ignore = true)
      @Mapping(target = "author", ignore = true)
      @Mapping(target = "image", ignore = true)
      Ad toAd(CreateOrUpdateAdDto dto); // конвертация получить или обновить объявление от автора
      @Mapping(target = "description", ignore = true)
      CreateOrUpdateAdDto toCreateOrUpdateAdDto(Ad ad); // конвертация обновленного объявления

      @Mapping(target = "pk", source = "id")
      @Mapping(target = "authorFirstName", source = "author.firstName")
      @Mapping(target = "authorLastName", source = "author.lastName")
      @Mapping(target = "email", source = "author.email")
      @Mapping(target = "phone", source = "author.phone")
      @Mapping(target = "description", ignore = true)
      ExtendedAdDto toExtendedAdDto(Ad ad); // конвертация объявления к расширенному объявлению

}
