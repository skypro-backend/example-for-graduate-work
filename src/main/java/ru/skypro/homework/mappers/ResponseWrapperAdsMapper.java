package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.model.ResponseWrapperAds;

@Mapper
public interface ResponseWrapperAdsMapper {
    ResponseWrapperAdsDto toResponseWrapperAdsDto(ResponseWrapperAds responseWrapperAds);
    ResponseWrapperAds toResponseWrapperAds(ResponseWrapperAdsDto responseWrapperAdsDto);
}
