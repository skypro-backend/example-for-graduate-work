package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;

@Mapper(componentModel = "spring")
public interface AddMapper {
    Ad toDTO(AdEntity adEntity);

    AdEntity toModel(Ad ad);
}
