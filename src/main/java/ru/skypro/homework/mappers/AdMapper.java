package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.store.entities.AdEntity;

import java.util.List;

@Mapper
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper( AdMapper.class );

    AdDTO toAdDto(AdEntity ad);
    CreateOrUpdateAdDTO toCreateOrUpdateDto(AdEntity ad);
    ExtendedAdDTO toExtendedAdDTO(AdEntity ad);
    List<ExtendedAdDTO> toListExtendedAdDTO(List<AdEntity> ad);
}
