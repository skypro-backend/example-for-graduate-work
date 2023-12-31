package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.mapping.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.AdFound;
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

    public AdFound getAdById(long id) {

        AdFound adFound = new AdFound();

        adFound.setAd(adRepository.findById(id).orElse(null));
        if (adFound.getAd() == null) {
            adFound.setHttpStatus(HttpStatus.NOT_FOUND);
        }

        return adFound;
    }
}

