package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.model.Ad;

import java.util.List;


/**
 * Service for working with ads.
 */
public interface AdService {
    /**
     * Returns all ads.
     *
     * @return all ads
     */
    List<Ad> getAllAds();

    /**
     * Returns the ad by id.
     *
     * @param id ad id
     * @return the ad
     */
    Ad getAdById(Long id);

    /**
     * Creates a new ad.
     *
     * @param dto       information about new ad
     * @param imageFile image file
     * @return created ad
     */
    Ad createAd(CreateOrUpdateAd dto, MultipartFile imageFile);

    /**
     * Updates the ad by id.
     *
     * @param id  ad id
     * @param dto new information
     * @return updated ad
     */
    Ad updateAd(Long id, CreateOrUpdateAd dto);

    /**
     * Deletes the ad by id.
     *
     * @param id ad id
     */
    void deleteAd(Long id);

    /**
     * Returns the ads of the current user.
     *
     * @return the ads of the current user
     */
    List<Ad> getMyAds();

    /**
     * Updates ad image by id.
     *
     * @param id        ad id
     * @param imageFile new image file
     * @return updated ad
     */
    Ad updateAdImage(Long id, MultipartFile imageFile);
}
