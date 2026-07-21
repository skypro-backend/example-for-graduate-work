package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ad;

@Mapper(
        componentModel = "spring",
        uses = IntegerToUserMapper.class
)
public interface AdMapper {

    @Mapping(source = "id", target = "pk")
    Ad toDto(
            ru.skypro.homework.entity.Ad ad
    );

    @Mapping(source = "pk", target = "id")
    ru.skypro.homework.entity.Ad toEntity(
            Ad ad
    );

}