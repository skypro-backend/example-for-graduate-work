package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.*;
import ru.skypro.homework.service.*;
import ru.skypro.homework.mapper.*;
import ru.skypro.homework.repository.*;
import ru.skypro.homework.Exceptions.NotFoundExpection.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса по работе с объявлениями
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;

    @Override
    public AdsDto getAllAds() {
        List<AdDto> adDto = adMapper.toAdsDto(adRepository.findAll());
        return new AdsDto();
    }

    @Override
    public AdDto createAd(CreateOrUpdateAdDto properties,
                          MultipartFile image,
                          Authentication authentication) throws IOException {

        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);

        Ad ad = adMapper.toAdEntity(properties);
        ad.setImage(imageService.downloadImage(image));
        ad.setAuthor(user);
        adRepository.save(ad);
        log.debug("Created ad " + ad);

        return adMapper.toAdDto(ad);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.findAdById(#id).getAuthor().getEmail()==authentication.name")
    public void updateImage(Integer id, MultipartFile image) throws IOException {

        Ad ad = findAdById(id);
        imageRepository.delete(ad.getImage());
        ad.setImage(imageService.downloadImage(image));
        adRepository.save(ad);
        log.debug("Update image ad with id - {}", id);
    }

    @Override
    public List<AdDto> getAllMyAds(Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);

        return adMapper.toAdsDto(adRepository.findAllByAuthorId(user.getId()));
    }

    @Override
    public ExtendedAdDto getAdFullInfo(Integer id) {
        return adMapper.toExtendAdDto(findAdById(id));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.findAdById(#id).getAuthor().getEmail()==authentication.name")
    public void deleteById(Integer id) {

        Ad ad = findAdById(id);

        commentRepository.deleteAllByAdPk(ad.getPk());
        adRepository.deleteById(id);
        imageRepository.deleteById(Math.toIntExact(ad.getImage().getId()));

        log.debug("ad with id - {} was delete", id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.findAdById(#id).getAuthor().getEmail()==authentication.name")
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {

        Ad ad = findAdById(id);

        ad.setDescription(createOrUpdateAdDto.getDescription());
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setPrice(createOrUpdateAdDto.getPrice());
        adRepository.save(ad);

        log.debug("ad with id - {} was update", id);

        return adMapper.toAdDto(ad);
    }

    public Ad findAdById(Integer id) {

        Optional<Ad> ad = adRepository.findById(id);

        if (ad.isEmpty()) {
            log.error("Ad not found");
            throw new AdNotFoundException();
        } else {
            return ad.get();
        }
    }
}