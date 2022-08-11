package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsComment;

import java.util.List;

@Mapper
public interface AdsMapper {

    @Mapping(source = "author.id", target = "author")
    AdsDto adsToAdsDto(Ads ads);

    List<AdsDto> adsToAdsDto(List<Ads> ads);

    @Mapping(source = "author", target = "author.id")
    @Mapping(target = "description", ignore = true)
    Ads adsDtoToAds(AdsDto adsDto);

    List<Ads> adsDtoToAds(List<AdsDto> adsDto);

    @Mapping(target = "author", ignore = true)
    Ads createAdsToAds(CreateAds createAds);

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "pk.pk", target = "pk")
    AdsCommentDto adsCommentToAdsCommentDto(AdsComment adsComment);

    List<AdsCommentDto> adsCommentToAdsCommentDto(List<AdsComment> adsComment);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "pk", target = "pk.pk")
    @Mapping(target = "id", ignore = true)
    AdsComment adsCommentDtoToAdsComment(AdsCommentDto adsCommentDto);

    List<AdsComment> adsCommentDtoToAdsComment(List<AdsCommentDto> adsCommentDto);
}
