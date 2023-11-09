package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsMapper {
    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);
@Mapping(source = "user.countAd", target = "count")
@Mapping(source = "user.adList", target = "result")
    AdsDto toDTO(User user);
}
