package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

import java.util.List;

@Mapper
public interface AdMapper {

    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.login")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "image", source = "image.filePath")
    @Mapping(target = "pk", source = "id")
    ExtendedAdDto toDto(Ad ad);

    @Mapping(target = "image", source = "image.filePath")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "user.id")
    AdDto toAdDto(Ad ad);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    Ad createAdDtoToAd(CreateOrUpdateAdDto createOrUpdateAdDto, User user, Image image);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "user", source = "user")
    Ad updateAdDtoToAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, User user);

    List<AdDto> toAdsDto(List<Ad> list);

    AdImage toAdImage(MultipartFile file);
}
