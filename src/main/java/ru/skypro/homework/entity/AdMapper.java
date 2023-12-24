package ru.skypro.homework.entity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
//    AdEntity adToAdEntity(AdDto adDto);
}
