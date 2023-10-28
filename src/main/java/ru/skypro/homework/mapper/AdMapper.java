package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdImage;

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

    List<AdDto> toAdsDto(List<Ad> list);

    AdImage toAdImage(MultipartFile file);
}
