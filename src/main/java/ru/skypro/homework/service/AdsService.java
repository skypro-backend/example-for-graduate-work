package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.io.IOException;
import java.util.List;

/**
 * CRUD-methods for managing Ads on platform
 */
public interface AdsService {

    /**
     * Method creates new add.
     * @param imageFiles image that client posts in add
     * @param createOrUpdateAd All the text of the add
     * @param authentication Authentication data.
     * @return Returns new add for the platform
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    AdsDto create(MultipartFile imageFiles, CreateOrUpdateAd createOrUpdateAd, Authentication authentication) throws IOException;

    /**
     * Method to update the adds text
     * @param id ID of the add that need an update
     * @param createOrUpdateAd All the text of the add
     * @return Returns Updated add
     */
    AdsDto update(Integer id, CreateOrUpdateAd createOrUpdateAd);

    /**
     * Method to update the adds image
     * @param id ID of the add that need an update
     * @param imageFile The new image to update the old one with
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */

    void updateAdsImage(Integer id, MultipartFile imageFile) throws IOException;

    /**
     * Method to get the list of adds
     * @param authentication Authentication data.
     * @return Returns the list of adds by authentication data
     */
    List<AdsDto> get(Authentication authentication);

    /**
     * Method to get the add by its ID
     * @param id Id of the add to search
     * @return Returns the add by ID
     */

    ExtendedAd getAdsById(Integer id);

    /**
     * Method to get list of all adds
     * @return Returns list of all ads on the platform
     */

    List<AdsDto> getAllAds();

    /**
     * Method to delete the add
     * @param id ID of the add to be deleted
     */

    void remove(Integer id);
}
