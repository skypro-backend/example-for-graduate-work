package ru.skypro.homework.models.mappers;

import org.mapstruct.*;
import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;
import ru.skypro.homework.models.entity.Ads;
import ru.skypro.homework.models.entity.Images;
import ru.skypro.homework.models.entity.User;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;

@Mapper(componentModel = "spring")
public interface AdsMapper {

    @Mapping(target = "author", source = "ads.author.id")
    @Mapping(target = "image", expression = "java(\"/image/\" + ads.getImage().getPk())")
    AdsDto toAdsDto(Ads ads);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", expression = "java(\"/image/\" + ads.getImage().getPk())")
    FullAdsDto toFullAdsDto(Ads ads);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "image", ignore = true)
    Ads fromAdsDto(AdsDto adsDto, @Context UserRepository repository, @Context ImageRepository imagesRepository);

    @Mapping(target = "pk", ignore = true)
    Ads fromCreateAds(CreateAdsDto createAdsDto, User author, Images image);

    @AfterMapping
    default void fromAdsDto(@MappingTarget Ads ads, AdsDto adsDto, @Context UserRepository repository, @Context ImageRepository imagesRepository) {
        ads.setAuthor(repository.findById(adsDto.getAuthor()).get());
        //  ads.setImage(imagesRepository.findImagesByData(adsDto.getImage().getBytes()));
    }

    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "image.pk", source = "image")
    Ads toAds(AdsDto adsDto);

}
