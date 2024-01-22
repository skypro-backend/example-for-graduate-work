package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.config.GetAuthentication;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.List;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;
    @Override
    public AdsDto getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return adMapper.adListToAds(ads);
    }

    @Override
    public AdDto addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, Authentication authentication) {
        Ad ad = adMapper.createOrUpdateAdToAd(createOrUpdateAd);
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());

        ad.setAuthor(user);
        ad.setImage(imageService.uploadImage(image));
        adRepository.save(ad);

        return adMapper.adToAdDto(ad);
    }

    @Override
    public ExtendedAd getAd(long id) {
        return adMapper.toExtendedAd(adRepository.findById(id).orElseThrow(()->
                new NotFoundException("Объявление с ID = " + id + " не найдено ")));
    }

    @Override
    @Transactional
    public void deleteAd(long id, Authentication authentication){

        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID" + id + "не найдено"));

        checkPermit(ad,authentication);
        commentRepository.deleteCommentsByAdId(id);
        imageRepository.deleteById(ad.getImage().getId());
        adRepository.deleteById(id);

    }

    @Override
    public AdDto updateAd(long id, CreateOrUpdateAd createOrUpdateAd, Authentication authentication) {
        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID" + id + "не найдено"));
        checkPermit(ad, authentication);
        ad.setTitle(createOrUpdateAd.getTitle());
        ad.setDescription(createOrUpdateAd.getDescription());
        ad.setPrice(createOrUpdateAd.getPrice());
        adRepository.save(ad);
        return adMapper.adToAdDto(ad);
    }

    @Override
    public AdsDto getAdsMe(Authentication authentication) {
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        List<Ad> adList = adRepository.findAdByAuthorId(user.getId());
        return adMapper.adListToAds(adList);
    }

    @Override
    @Transactional
    public void updateAdImage(Long id, MultipartFile image, Authentication authentication){
        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID" + id + "не найдено"));
        checkPermit(ad, authentication);
        Image imageFile = ad.getImage();
        ad.setImage(imageService.uploadImage(image));
        imageService.removeImage(imageFile);
        adRepository.save(ad);
    }

    public void checkPermit(Ad ad, Authentication authentication){
        if (!ad.getAuthor().getEmail().equals(authentication.getName())
                && !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            throw new AccessDeniedException("Вы не можете редактировать или удалять чужое объявление");
        }
    }
}
