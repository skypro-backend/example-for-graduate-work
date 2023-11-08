package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.model.Ad;

import java.util.List;

@Mapper
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
    @Mapping(source = "user_id", target = "author")
    AdDto toDto(Ad ad);
    @Mapping(source = "author", target = "user_id")
    Ad toModel(AdDto adDto);

}
