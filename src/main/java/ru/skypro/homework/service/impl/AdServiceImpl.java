package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.entities.AdEntity;
import ru.skypro.homework.service.repositories.AdRepository;

import javax.imageio.stream.ImageInputStreamImpl;
import javax.xml.crypto.OctetStreamData;
import java.io.OutputStream;
import java.util.List;

@AllArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    private final AdMapper adMapper;
    private final AdRepository adRepository;

    @Override
    public AdsDTO getAllAds() {
        List<AdEntity> adEntity = adRepository.findAll();
        return adMapper.toAdsDto(adEntity);
    }

    @Override
    public AdDTO addAd(AdDTO ad, MultipartFile image) {
        AdEntity adEntity = new AdEntity();
        adEntity.setPrice(ad.getPrice());
        adEntity.setTitle(ad.getTitle());
        adEntity.setAuthor(ad.getAuthor());
/*
        image.getInputStream().
*/
        adRepository.save(adEntity);
        return ad;
    }

    @Override
    public ExtendedAdDTO getInfoByAd(int id) {
        return adMapper.toExtendedAdDTO(adRepository.getReferenceById(id));
    }

    @Override
    public void deleteUser(int id) {
        adRepository.deleteById(id);
    }

    @Override
    public AdDTO updateInfoByAd(int id, CreateOrUpdateAdDTO ad) {
        AdEntity adEntity= adRepository.getReferenceById(id);
        adEntity.setDescription(ad.getDescription());
        adEntity.setPrice(ad.getPrice());
        adEntity.setTitle(adEntity.getTitle());
        adRepository.save(adEntity);
        return adMapper.toAdDto(adEntity);
    }

    @Override
    public AdsDTO getAdsByAuthUser() {
        //
        return null;
    }

    @Override
    public OctetStreamData updateImage(int id, MultipartFile image) {
        //
        return null;
    }

}
