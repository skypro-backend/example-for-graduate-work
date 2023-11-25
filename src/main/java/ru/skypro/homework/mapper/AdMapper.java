package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.entity.AdEntity;

@Mapper
public interface AdMapper {
    Ad toDTO(AdEntity adEntity);
}
