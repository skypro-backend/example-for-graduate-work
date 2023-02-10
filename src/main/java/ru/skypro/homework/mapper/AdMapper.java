package ru.skypro.homework.mapper;


import java.util.Collection;
import org.mapstruct.Mapper;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.entity.Ad;

/**
 * маппер для {@link Ad} готовый рекорд {@link AdsDTO}
 */

@Mapper(componentModel = "spring")
public interface AdMapper {

  Ad toEntity(AdsDTO adDto);

  AdsDTO toDTO(Ad ad);

  Collection<Ad> toEntityList(Collection<AdsDTO> adDTOS);

  Collection<AdsDTO> toDTOList(Collection<Ad> ads);
}
