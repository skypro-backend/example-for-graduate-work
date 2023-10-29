package ru.skypro.homework.mapper.impl;

import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdModel;

public class AdMapperImpl implements AdMapper {

    @Override
    public AdDto toDTO(AdDto model) {
        if ( model == null ) {
            return null;
        }

        AdDto adDto = new AdDto();

        return adDto;
    }

    @Override
    public AdDto toDTO(AdModel model) {
        return null;
    }

    @Override
    public AdDto toModel(AdDto dto) {
        if ( dto == null ) {
            return null;
        }

        AdDto adDto = new AdDto();

        return adDto;
    }
}