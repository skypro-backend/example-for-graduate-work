package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.model.Advert;
import ru.skypro.homework.model.User;

import java.util.List;

@Mapper
public interface AdvertMapper {
    AdDto advertToAdvertDto(Advert advert);
    Advert advertDtoToAdvert(AdDto advertDto);

    default int map(User user) {
        return user != null ? user.getId() : 0;
    }

    default User map(int authorId) {
        User user = new User();
        user.setId(authorId);
        return user;
    }

    Advert updateAdFromDto(AdDto advertDto, @MappingTarget Advert advert);

    List<AdDto> advertsToAdvertDtos(List<Advert> adverts);
}
