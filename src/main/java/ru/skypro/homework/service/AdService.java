package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.Ad;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AdService {
    Ad createAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image) throws IOException;
    Ads getAllAdvertising();
    ExtendedAd getAdvertisingById(int id);
    boolean deleteAdvertisingById(int id) throws IOException;
    Ad updateAdvertising(int id, CreateOrUpdateAd createOrUpdateAd);
    Ads getAllAuthenticatedUserAdvertising();
    boolean updateAdvertisingImage(int id, MultipartFile image) throws IOException;
    void downloadAdImageFromDB(int adId, HttpServletResponse response) throws IOException;
    Ads findByTitle(String title);
}
