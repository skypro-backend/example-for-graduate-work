package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.exceptions.PermissionDeniedException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository usersRepository;
    private final ImagesRepository imagesRepository;
    private final AdMapper adMapper;

    @Override
    @Transactional
    public Ads getAllAds() {
        Ads allAds = new Ads();
        List<AdEntity> list = new ArrayList<>();
        adRepository.findAll().forEach(list::add);
        allAds.setCount(list.size());
        allAds.setResults(list.stream().map(adMapper::adEntityToAd).collect(Collectors.toList()));
        return allAds;
    }

    @Override
    @Transactional
    public Ad postAd(CreateOrUpdateAd properties, MultipartFile file, String userName) {
        UserEntity author = usersRepository.findByUsername(userName);
        AdEntity adEntity = new AdEntity();
        adEntity.setTitle(properties.getTitle());
        adEntity.setDescription(properties.getDescription());
        adEntity.setPrice(properties.getPrice());
        adEntity.setAuthor(author);
        Image image = new Image();
        try {
            byte[] bytes = file.getBytes();
            image.setImage(bytes);
        } catch (IOException e) {
            log.error("Failed to transform incoming image to bytes", e);
        }
        imagesRepository.save(image);
        adEntity.setImage(image);

        adRepository.save(adEntity);
        return adMapper.adEntityToAd(adEntity);
    }

    @Override
    @Transactional
    public ExtendedAd getAdById(int id) throws AdNotFoundException {
        return adRepository.findById(id).map(adMapper::adEntityToExtendedAd)
                .orElseThrow(() -> new AdNotFoundException("Not found ad with id = " + id));
    }

    @Override
    @Transactional
    public void deleteAdById(int id, String userName) {
        UserEntity user = usersRepository.findByUsername(userName);
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new AdNotFoundException("Not found ad with id = " + id));
        if (!checkPermission(adEntity, user)) {
            throw new PermissionDeniedException("You do not have permission to delete ad with id = " + id);
        }
        adRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Ad patchAdById(int id, CreateOrUpdateAd createOrUpdateAd, String userName)  {
        UserEntity user = usersRepository.findByUsername(userName);
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new AdNotFoundException("Not found ad with id = " + id));
        if (!checkPermission(adEntity, user)) {
            throw new PermissionDeniedException("You do not have permission to update ad with id = " + id);
        }
        adEntity.setTitle(createOrUpdateAd.getTitle());
        adEntity.setPrice(createOrUpdateAd.getPrice());
        adEntity.setDescription(createOrUpdateAd.getDescription());
        AdEntity updatedAdEntity = adRepository.save(adEntity);
        return adMapper.adEntityToAd(updatedAdEntity);
    }

    @Override
    @Transactional
    public Ads getMyAds(String userName) {
        UserEntity author = usersRepository.findByUsername(userName);
        List<AdEntity> list = adRepository.findAllByAuthor(author);
        Ads myAds = new Ads();
        myAds.setCount(list.size());
        myAds.setResults(list.stream().map(adMapper::adEntityToAd).collect(Collectors.toList()));
        return myAds;
    }

    @Override
    @Transactional
    public byte[] patchAdsImageById(int id, MultipartFile file, String userName) {

        UserEntity user = usersRepository.findByUsername(userName);
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new AdNotFoundException("Not found ad with id = " + id));
        if (!checkPermission(adEntity, user)) {
            throw new PermissionDeniedException("You do not have permission to update image in ad with id = " + id);
        }

        Image image = adEntity.getImage();
        try {
            byte[] bytes = file.getBytes();
            image.setImage(bytes);
        } catch (IOException e) {
            log.error("Failed to transform incoming image to bytes", e);
        }
        imagesRepository.save(image);
        return image.getImage();
    }

    private boolean checkPermission(AdEntity ad, UserEntity user) {
        return ad.getAuthor().equals(user) || user.getRole() == Role.ADMIN;
    }
}
