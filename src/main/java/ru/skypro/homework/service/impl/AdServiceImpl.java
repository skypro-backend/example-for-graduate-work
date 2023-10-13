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
import ru.skypro.homework.service.repositories.CommentRepository;
import javax.xml.crypto.OctetStreamData;
import java.util.List;

@AllArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;

    @Override
    public AdsDTO getAllAds() {
        List<AdEntity> adEntity = adRepository.findAll();
        return adMapper.toAdsDto(adEntity);
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO ad, MultipartFile image) {
        AdEntity adEntity = new AdEntity();
        adEntity.setDescription(ad.getDescription());
        adEntity.setTitle(ad.getTitle());
        adEntity.setPrice(ad.getPrice());
        //adEntity.setUser(); получение авторизированного пользователся
/*
        image.getInputStream().
*/
        adEntity.setImage(null);
        adRepository.save(adEntity);
        return adMapper.toAdDto(adEntity);
    }

    @Override
    public ExtendedAdDTO getInfoByAd(int id) {
        return adMapper.toExtendedAdDTO(adRepository.findById(id).get());
    }

    @Override
    public void deleteAd(int id) {
        commentRepository.deleteAllByAdEntity(adRepository.findById(id).get());
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
    public AdsDTO getAdsByAuthUser(String email) {
        return adMapper.toAdsDto(adRepository.findByEmail(email));
    }

    @Override
    public OctetStreamData updateImage(int id, MultipartFile image) {
        //
        return null;
    }

}
