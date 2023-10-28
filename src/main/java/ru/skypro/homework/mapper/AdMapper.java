package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.util.List;

/**
 * Маппинг сущности объявления
 */
@Mapper (componentModel = "spring")
public interface AdMapper {

      @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
      @Mapping(target = "pk", source = "id")
      @Mapping(target = "author", source = "author.id")
      AdDto toAdDto(Ad ad); // конвертация сущности в DTO

      @Named("imageToPathString")
      default String imageToPathString(Image image) {
            return "/ads/image/" + image.getId();
      }

      List <AdDto> toAdsDto(List<Ad> ads);

      @Mapping(target = "id", ignore = true)
      @Mapping(target = "author", ignore = true)
      @Mapping(target = "image", ignore = true)
      Ad toAdCreate(CreateOrUpdateAdDto dto); // конвертация получить или обновить объявление от автора

      @Mapping(target = "pk", source = "id")
      @Mapping(target = "authorFirstName", source = "author.firstName")
      @Mapping(target = "authorLastName", source = "author.lastName")
      @Mapping(target = "email", source = "author.email")
      @Mapping(target = "phone", source = "author.phone")
      @Mapping(target = "description", ignore = true)
      @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
      ExtendedAdDto toExtendedAdDto(Ad ad); // конвертация объявления к расширенному объявлению

      @Named("authorToInt")
      default Integer authorToInt(User user) {
            return user.getId();
      }

}
