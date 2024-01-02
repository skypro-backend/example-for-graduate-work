package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.mapping.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.utils.AdFound;
import ru.skypro.homework.model.utils.AdsFound;
import ru.skypro.homework.model.utils.ImageProcessResult;
import ru.skypro.homework.repository.AdRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h2>AdvertisementsService</h2>Service providing tools to manage advertisements
 */
@RequiredArgsConstructor
@Service
public class AdvertisementsService {
    private final AdRepository adRepository;

    /**
     * <h2>getAll()</h2><br>
     * Gets all advertisements from repository
     *
     * @return AdsDto {@link AdsDto} list of entities and number of entities
     */
    public AdsDto getAll() {
        AdsDto adsDto = new AdsDto();

        List<AdDto> adDtoList = adRepository.findAll()
                .stream()
                .map(AdMapper.INSTANCE::adToDto)
                .collect(Collectors.toList());

        adsDto.setCount(adDtoList.size());
        adsDto.setResults(adDtoList);

        return adsDto;
    }

    /**
     * <h2>addNewAd</h2>
     *
     * @param ad DTO ({@link AdDto}) with data to create a new advertisement ({@link ru.skypro.homework.model.Ad} entity)
     * @return DTO of created ad entity
     */
    public AdDto addNewAd(AdDto ad) {
        Ad newAd = new Ad(Long.valueOf(ad.getPk()), ad.getAuthor(), ad.getImage(), ad.getPrice(),
                ad.getTitle());
        newAd = adRepository.save(newAd);
        return AdMapper.INSTANCE.adToDto(newAd);
    }

    /**
     * <h2>getAdById</h2>
     *
     * @param id advertisement identifier
     * @return {@link AdFound} instance
     */
    public AdFound getAdById(long id) {

        AdFound adFound = new AdFound();

        adFound.setAd(adRepository.findById(id).orElse(null));
        if (adFound.getAd() == null) {
            adFound.setHttpStatus(HttpStatus.NOT_FOUND);
        }

        return adFound;
    }

    /**
     * <h2>removeAd(long id)</h2>
     *
     * @param id advertisement identifier
     * @return {@link AdFound}
     */
    public AdFound removeAd(long id) {
        AdFound adRemoved = getAdById(id);
        if (adRemoved.getHttpStatus().is4xxClientError()) {
            return adRemoved;
        }
        adRepository.delete(adRemoved.getAd());
        return adRemoved;
    }

    public AdsFound getAdsDtoByUserId(long id, Authentication auth) {
        String name = auth.getName();
        auth.getAuthorities();
        AdsFound adsFound = new AdsFound();
        List<Ad> listOfAdvertisements = adRepository.findByAuthor(id);
        AdsDto adsDto = new AdsDto();

        if (listOfAdvertisements.isEmpty()) {
            adsDto.setCount(0);
            adsDto.setResults(null);

            adsFound.setResult(adsDto);
            adsFound.setHttpStatus(HttpStatus.NOT_FOUND);

            return adsFound;
        }

        adsDto.setResults(
                listOfAdvertisements.stream()
                        .map(AdMapper.INSTANCE::adToDto).collect(Collectors.toList()));
        adsDto.setCount(listOfAdvertisements.size());

        adsFound.setResult(adsDto);
        adsFound.setHttpStatus(HttpStatus.OK);

        return adsFound;
    }

    /**
     * <h2>updateAd</h2>
     *
     * @param id               advertisement identifier
     * @param updatedAdContent {@link CreateOrUpdateAdDto}
     * @return {@link AdFound} result of update
     */
    public AdFound updateAd(long id, CreateOrUpdateAdDto updatedAdContent) {

        AdFound adUpdateResult = new AdFound();
        Ad adById = adRepository.findById(id).orElse(null);

        if (adById == null) {
            return AdFound.adNotFound();
        }

        adById.setTitle(updatedAdContent.getTitle());
//            adById.setImage(updatedAdContent.getDescription());
        // todo: learn what does CreateOrUpdateAdDto description field mean
        adUpdateResult.setAd(adById);
        adUpdateResult.setHttpStatus(HttpStatus.OK);
        return adUpdateResult;
    }

    public ImageProcessResult getPhotoByAdId(long id) {
        ImageProcessResult result = new ImageProcessResult();
        result.setImage(null);
        result.setHttpStatus(HttpStatus.NOT_FOUND);
        //todo: fix getPhotoByAdId method in AdvertisementService
        return result;
    }
}

