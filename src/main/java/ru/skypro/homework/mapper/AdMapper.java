package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.entity.Ad;

@Mapper
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    AdDTO adToAdDTO(Ad ad);

    Ad adDTOToAd(AdDTO adDTO);
}