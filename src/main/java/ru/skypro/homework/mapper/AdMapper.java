package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;

@Mapper(componentModel = "spring", uses = UserMapper.class, imports = {java.util.Objects.class})
public interface AdMapper {

    @Mapping(source = "author.id", target = "author")
    AdDto toDto(Ad ad);

    @Mapping(target = "author", ignore = true)
    Ad toEntity(AdDto adDto);

    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    ExtendedAdDto toExtendedAdDto(Ad ad);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "author", ignore = true)
    void updateEntityFromDto(CreateOrUpdateAdDto createOrUpdateAdDto, @MappingTarget Ad ad);
}
