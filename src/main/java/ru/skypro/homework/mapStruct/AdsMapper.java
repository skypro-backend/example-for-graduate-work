package ru.skypro.homework.mapStruct;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsComment;

import java.util.List;

@Mapper
public interface AdsMapper {

    AdsDto adsToAdsDto(Ads ads);

    List<AdsDto> adsToAdsDto(List<Ads> ads);

    Ads adsDtoToAds(AdsDto adsDto);

    List<Ads> adsDtoToAds(List<AdsDto> adsDto);

    Ads createAdsToAds(CreateAds createAds);

    AdsCommentDto adsCommentToAdsCommentDto(AdsComment adsComment);

    List<AdsCommentDto> adsCommentToAdsCommentDto(List<AdsComment> adsComment);

    AdsComment adsCommentDtoToAdsComment(AdsCommentDto adsCommentDto);

    List<AdsComment> adsCommentDtoToAdsComment(List<AdsCommentDto> adsCommentDto);
}
