package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import ru.skypro.homework.dto.AdDto;
import org.mapstruct.Mapper;
import ru.skypro.homework.model.AdModel;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdMapper {
    AdDto toDTO(AdDto model);

    AdDto toDTO(AdModel model);

    AdDto toModel(AdDto dto);
}