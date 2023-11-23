package ru.skypro.homework.mapper;

import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.model.Ad;

import java.util.List;

//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsMapper {
    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);
//@Mapping(source = "user.countAd", target = "count")
//@Mapping(source = "user.adList", target = "results")
//    AdsDto toDTO(User user);
//@Mapping(source = "user.adList", target = "results")
List<AdDto> toDto(int size, List<Ad> adList);

//    List<AdDto> toDTO(List<Ad> adList);
}
