package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.model.Ad;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "author.id", source = "author")
    Ad toEntity(AdDTO adDTO);
}
