package ru.skypro.homework.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;
import ru.skypro.homework.models.entity.Ads;
import ru.skypro.homework.models.entity.Comments;
import ru.skypro.homework.models.entity.Images;
import ru.skypro.homework.models.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdsMapper {

    @Mapping(target = "author", source = "ads.author.id")
    @Mapping(target = "image", expression = "java(\"/image/\" + ads.getImage().getPk())")
    AdsDto toAdsDto(Ads ads);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", expression = "java(\"http://127.0.0.1:8080/image/\" + ads.getImage().getPk())")
    FullAdsDto toFullAdsDto(Ads ads);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "image", source = "images")
    @Mapping(target = "pk", source = "adsDto.pk")
    Ads fromAdsDto(AdsDto adsDto, User author, Images images, List<Comments> comments);

    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Ads fromCreateAds(CreateAdsDto createAdsDto, User author, Images image);
}
