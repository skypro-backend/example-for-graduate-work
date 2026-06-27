package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса управления объявлениями {@link AdService}.
 */
@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final AdMapper adMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Ads getAllAds() {
        List<Ad> adList = adRepository.findAll().stream()
                .map(adMapper::toAdDto)
                .collect(Collectors.toList());
        Ads ads = new Ads();
        ads.setCount(adList.size());
        ads.setResults(adList);
        return ads;
    }

    /**
     * {@inheritDoc}
     * @throws RuntimeException в случае ошибки при сохранении файла изображения на диск
     */
    @Override
    public Ad addAd(CreateOrUpdateAd dto, MultipartFile image, String username) {
        UserEntity author = userRepository.findByEmail(username).orElseThrow();
        AdEntity adEntity = adMapper.toEntity(dto);
        adEntity.setAuthor(author);

        try {
            String fileName = imageService.saveImage(image);
            adEntity.setImage("/images/" + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка сохранения файла", e);
        }

        return adMapper.toAdDto(adRepository.save(adEntity));
    }

    /**
     * {@inheritDoc}
     * @throws java.util.NoSuchElementException если объявление с указанным ID не найдено
     */
    @Override
    @Transactional(readOnly = true)
    public ExtendedAd getAd(Integer id) {
        return adRepository.findById(id)
                .map(adMapper::toExtendedAdDto)
                .orElseThrow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAd(Integer id) {
        adRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     * @throws java.util.NoSuchElementException если объявление с указанным ID не найдено
     */
    @Override
    public Ad updateAd(Integer id, CreateOrUpdateAd dto) {
        AdEntity ad = adRepository.findById(id).orElseThrow();
        ad.setTitle(dto.getTitle());
        ad.setPrice(dto.getPrice());
        ad.setDescription(dto.getDescription());
        return adMapper.toAdDto(adRepository.save(ad));
    }

    /**
     * {@inheritDoc}
     * @throws java.util.NoSuchElementException если пользователь с указанным email не найден
     */
    @Override
    @Transactional(readOnly = true)
    public Ads getAdsMe(String username) {
        UserEntity author = userRepository.findByEmail(username).orElseThrow();
        List<Ad> adList = adRepository.findAllByAuthorId(author.getId()).stream()
                .map(adMapper::toAdDto)
                .collect(Collectors.toList());
        Ads ads = new Ads();
        ads.setCount(adList.size());
        ads.setResults(adList);
        return ads;
    }

    /**
     * {@inheritDoc}
     * @throws java.util.NoSuchElementException если объявление с указанным ID не найдено
     * @throws RuntimeException в случае ошибок ввода-вывода при замене картинки
     */
    @Override
    public void updateAdImage(Integer id, MultipartFile image) {
        AdEntity ad = adRepository.findById(id).orElseThrow();
        try {
            String fileName = imageService.saveImage(image);
            ad.setImage("/images/" + fileName);
            adRepository.save(ad);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка обновления файла", e);
        }
    }

    /**
     * {@inheritDoc}
     * @throws java.util.NoSuchElementException если объявление или пользователь не найдены
     */
    @Override
    public boolean isAuthorOrAdmin(Integer id, String username) {
        AdEntity ad = adRepository.findById(id).orElseThrow();
        return ad.getAuthor().getEmail().equals(username) ||
                userRepository.findByEmail(username).get().getRole() == Role.ADMIN;
    }
}