package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.Collection;


@Mapper (componentModel = "spring")
public interface AdMapper {


    @Mapping(target = "author", source = "adEntity.author.id")
    @Mapping(target = "image", source = "adEntity.photo.filePath")
    AdDTO adEntityToAddDTO (AdEntity adEntity);


    Collection<AdDTO> adToAdListDto(Collection<AdEntity> ads);

    @Mapping(target = "image", source = "adEntity.photo.filePath")
    ExtendedAdDTO adEntityAndUserEntityToExtendedAdDTO(AdEntity adEntity);


    @Mapping(target = "author.id", source = "userEntity.id")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "id", ignore = true)
    AdEntity adDTOAndUserEntityToAdEntity (UserEntity userEntity, CreateOrUpdateAdDTO createOrUpdateAdDTO, PhotoEntity photoEntity);
}
