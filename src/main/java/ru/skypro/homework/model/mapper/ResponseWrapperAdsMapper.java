package ru.skypro.homework.model.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.model.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.model.entity.ResponseWrapperAds;

@Mapper
public interface ResponseWrapperAdsMapper {
    ResponseWrapperAdsDto toResponseWrapperAdsDto(ResponseWrapperAds responseWrapperAds);
    ResponseWrapperAds toResponseWrapperAds(ResponseWrapperAdsDto responseWrapperAdsDto);
}
