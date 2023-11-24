package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.model.Ad;

import java.util.List;

@Mapper
//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsMapper {
    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);
//@Mapping(source = "user.countAd", target = "count")
//@Mapping(source = "user.adList", target = "results")
//    AdsDto toDTO(User user);
//@Mapping(source = "adList", target = "results")
//@Mapping(source = "size", target = "count")
//AdsDto toDto(int size, List<Ad> adList);

    //    List<AdDto> toDTO(List<Ad> adList);
    @Mapping(target = "author", source = "user.id")
    List<AdDto> toDto(List<Ad> adMeList);
}
