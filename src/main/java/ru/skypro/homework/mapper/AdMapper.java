package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.api.dto.Ad;
import ru.skypro.homework.api.dto.Ads;
import ru.skypro.homework.api.dto.CreateOrUpdateAd;
import ru.skypro.homework.api.dto.ExtendedAd;
import ru.skypro.homework.db.model.AdModel;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    Ad toDto(AdModel model);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    ExtendedAd toExtendedDto(AdModel model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    AdModel toModel(CreateOrUpdateAd dto);

    List<Ad> toDtoList(List<AdModel> models);

    default Ads toAds(List<AdModel> models) {
        List<Ad> results = models == null ? new ArrayList<>() : toDtoList(models);
        Ads ads = new Ads();
        ads.setCount(results.size());
        ads.setResults(results);
        return ads;
    }
}
