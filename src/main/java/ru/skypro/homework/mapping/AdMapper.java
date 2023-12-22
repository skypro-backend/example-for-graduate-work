package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;

@Mapper
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
    AdEntity adToAdEntity(AdDto ad);
}
