package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.entity.UserEntity;


@Mapper (componentModel = "spring")
public interface AdMapper {


    @Mapping(target = "author", source = "adEntity.author.id")
    @Mapping(target = "image", source = "adEntity.photo.path")
    AdDTO adEntityToAddDTO (AdEntity adEntity);


    @Mapping(target = "authorFirstName", source = "userEntity.firstName")
    @Mapping(target = "authorLastName", source = "userEntity.lastName")
    @Mapping(target = "image", source = "adEntity.photo.path")
    ExtendedAdDTO adEntityAndUserEntityToExtendedAdDTO(UserEntity userEntity, AdEntity adEntity);


    @Mapping(target = "author", source = "userEntity")
    @Mapping(target = "description", ignore = true)
    AdEntity adDTOAndUserEntityToAdEntity (UserEntity userEntity, AdDTO adDTO, PhotoEntity photoEntity);
}
