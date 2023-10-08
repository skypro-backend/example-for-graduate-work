package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;

import javax.xml.crypto.OctetStreamData;
import java.util.List;

public interface AdService {
    AdsDTO getAllAds();

    AdDTO addAd(CreateOrUpdateAdDTO ad, MultipartFile image);

    ExtendedAdDTO getInfoByAd(int id);

    void deleteUser(int id);

    AdDTO updateInfoByAd(int id, CreateOrUpdateAdDTO ad);

    AdsDTO getAdsByAuthUser();

    OctetStreamData updateImage(int id, MultipartFile image);

}
