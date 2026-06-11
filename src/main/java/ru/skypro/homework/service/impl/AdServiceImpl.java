package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads.AdDto;
import ru.skypro.homework.dto.Ads.AdsDto;
import ru.skypro.homework.dto.Ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.Ads.ExtendedAdDto;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.security.AccessChecker;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserService userService;
    private final ImageService imageService;
    private final AccessChecker accessChecker;

    @Override
    @Transactional(readOnly = true)
    public AdsDto getAllAds() {
        return adMapper.toAdsDto(adRepository.findAllWithAuthor());
    }

    @Override
    @Transactional(readOnly = true)
    public AdsDto getAdsByCurrentUser(String email) {
        User user = userService.getUserByEmail(email);
        return adMapper.toAdsDto(adRepository.findByAuthorIdWithAuthor(user.getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public ExtendedAdDto getAd(Integer id) {
        Ad ad = findAd(id);
        return adMapper.toExtendedDto(ad);
    }

    @Override
    @Transactional
    public AdDto createAd(String email, CreateOrUpdateAdDto properties, MultipartFile image) {
        User author = userService.getUserByEmail(email);
        Ad ad = adMapper.toEntity(properties);
        ad.setAuthor(author);
        ad = adRepository.save(ad);

        String imagePath = imageService.saveAdImage(ad.getId(), image);
        ad.setImage(imagePath);
        ad = adRepository.save(ad);

        return adMapper.toDto(ad);
    }

    @Override
    @Transactional
    public AdDto updateAd(String email, Integer id, CreateOrUpdateAdDto updateAd) {
        Ad ad = findAd(id);
        User currentUser = userService.getUserByEmail(email);
        accessChecker.checkAdOwnerOrAdmin(ad, currentUser);

        adMapper.updateEntity(updateAd, ad);
        ad = adRepository.save(ad);
        return adMapper.toDto(ad);
    }

    @Override
    @Transactional
    public void deleteAd(String email, Integer id) {
        Ad ad = findAd(id);
        User currentUser = userService.getUserByEmail(email);
        accessChecker.checkAdOwnerOrAdmin(ad, currentUser);
        adRepository.delete(ad);
    }

    @Override
    @Transactional
    public byte[] updateAdImage(String email, Integer id, MultipartFile image) {
        Ad ad = findAd(id);
        User currentUser = userService.getUserByEmail(email);
        accessChecker.checkAdOwnerOrAdmin(ad, currentUser);

        String imagePath = imageService.saveAdImage(ad.getId(), image);
        ad.setImage(imagePath);
        adRepository.save(ad);
        return imageService.readAdImage(ad.getId());
    }

    private Ad findAd(Integer id) {
        return adRepository.findByIdWithAuthor(id.longValue())
                .orElseThrow(NotFoundException::new);
    }
}
