package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.List;

@Mapper
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
    @Mapping(source = "user.id", target = "author")
    @Mapping(source = "ad.adImage", target = "image")
    @Mapping(target = "ad.description", ignore = true)
    @Mapping(target = "ad.countComment", ignore = true)
    @Mapping(target = "ad.commentList", ignore = true)
    AdDto toDto(Ad ad, User user);
//    @Mapping(target = "id", source = "adDto.author")
//    User toModel(AdDto adDto);
    @Mapping(target = "adImage", source = "adDto.image")
    Ad toModel(AdDto adDto);

}
