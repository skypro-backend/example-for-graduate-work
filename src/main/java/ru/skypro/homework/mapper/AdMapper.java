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
    @Mapping(source = "adImage", target = "image")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "countComment", ignore = true)
    @Mapping(target = "commentList", ignore = true)
    AdDto toDto(Ad ad);
    @Mapping(target = "user_id", source = "author")
    @Mapping(target = "adImage", source = "image")
    Ad toModel(AdDto adDto);

}
