package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.entities.AdEntity;
import ru.skypro.homework.service.entities.ImageEntity;
import ru.skypro.homework.service.repositories.AdRepository;
import ru.skypro.homework.service.repositories.CommentRepository;
import ru.skypro.homework.service.repositories.UserRepository;

import javax.xml.crypto.OctetStreamData;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final ImageService imageService;
    private final UserRepository userRepository;


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
        adEntity.setImage(imageService.downloadImage(image));
        adEntity.setUser(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
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
    public OctetStreamData updateImage(int id, MultipartFile image) throws IOException {
        AdEntity ad = adRepository.findById(id).get();
        ImageEntity imageEntity = imageService.downloadImage(image);
        ad.setImage(imageEntity);
        adRepository.saveAndFlush(ad);
        return new OctetStreamData(image.getInputStream());
    }

}
