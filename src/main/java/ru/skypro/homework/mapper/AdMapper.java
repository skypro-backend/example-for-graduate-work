package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import ru.skypro.homework.dto.AdDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdMapper {
    AdDto toDTO(AdDto model);

    AdDto toModel(AdDto dto);
}