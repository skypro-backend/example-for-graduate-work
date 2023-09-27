package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.store.entities.AdEntity;
@Mapper
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper( AdMapper.class );

    AdDTO toDto(AdEntity ad);
}
