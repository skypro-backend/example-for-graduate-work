package ru.skypro.homework.service.ads.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.out.AdDto;
import ru.skypro.homework.dto.ads.out.AdsDto;
import ru.skypro.homework.dto.ads.in.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.out.ExtendedAdDto;
import ru.skypro.homework.entity.ads.Ad;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.ads.AdsRepository;
import ru.skypro.homework.service.ads.AdsService;
import ru.skypro.homework.service.image.ImageService;
import ru.skypro.homework.service.users.UserService;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdMapper adMapper;

    private final AdsRepository adsRepository;

    private final ImageService imageService;
    private final UserService userService;

    public AdsServiceImpl(AdMapper adMapper,
                          AdsRepository adsRepository,
                          ImageService imageService,
                          UserService userService
    ) {
        this.adMapper = adMapper;
        this.adsRepository = adsRepository;
        this.imageService = imageService;
        this.userService = userService;
    }

    @Override
    public AdsDto getAllAds() {
        List<Ad> adList = adsRepository.findAll();
        if (adList.isEmpty()) {
            return null;
        } else {
            return adMapper.toAdsDto(adList);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) {
        User author = userService.getAuthor();
        try {
            String imagePath = imageService.consumeImageOfGoods(image);
            Ad ad = adMapper.toAd(createOrUpdateAdDto);
            ad.setAuthor(author);
            ad.setImage(imagePath);
            adsRepository.save(ad);
            return adMapper.toAdDto(ad);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public AdsDto getAdsMe() {
        User author = userService.getAuthor();
        List<Ad> adsList = adsRepository.findAllByAuthor(author);
        return adMapper.toAdsDto(adsList);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN') or (hasAuthority('USER') and @authServiceImpl.isUserAllowedToChangeAds(authentication, #id))")
    public ExtendedAdDto getAds(Integer id) {
        Optional<Ad> adOptional = adsRepository.findByPkIs(id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Объявление с таким id не найдено: " + id);
        } else {
            Ad ad = adOptional.get();
            return adMapper.toExtendedAdDto(ad);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN') or (hasAuthority('USER') and @authServiceImpl.isUserAllowedToChangeAds(authentication, #id))")
    public AdDto updateAds(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        User author = userService.getAuthor();
        Optional<Ad> adOptional = adsRepository.findByAuthorAndPk(author, id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Объявление с таким id не найдено: " + id);
        } else {
            Ad adTarget = adOptional.get();
            adMapper.updateAds(createOrUpdateAdDto, adTarget);
            adsRepository.save(adTarget);
            return adMapper.toAdDto(adTarget);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN') or (hasAuthority('USER') and @authServiceImpl.isUserAllowedToChangeAds(authentication, #id))")
    public void removeAd(Integer id) {
        User author = userService.getAuthor();
        Optional<Ad> adOptional = adsRepository.findByAuthorAndPk(author, id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Объявление с таким id не найдено: " + id);
        } else {
            Ad ad = adOptional.get();
            adsRepository.delete(ad);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN') or (hasAuthority('USER') and @authServiceImpl.isUserAllowedToChangeAds(authentication, #id))")
    public byte[] updateImage(Integer id, MultipartFile image) throws IOException {
        Optional<Ad> adOptional = adsRepository.findByPkIs(id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Объявление с таким id не найдено: " + id);
        } else {
            Ad ad = adOptional.get();
            String currentUrlToImage = ad.getImage();

            if (currentUrlToImage != null) {
                imageService.deleteImageOfGoods(currentUrlToImage);
            }
            String newUrlToImage = imageService.consumeImageOfGoods(image);

            ad.setImage(newUrlToImage);
            adsRepository.save(ad);

            Path fullPathToImage = imageService.getFullPathToImageOfGoods(newUrlToImage);
            return imageService.imageToByteArray(fullPathToImage);
        }
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public byte[] getImage(Integer id) throws IOException {
        Optional<Ad> adOptional = adsRepository.findByPkIs(id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Объявление с таким id не найдено: " + id);
        } else {
            String urlToImage = adOptional.get().getImage();

            if (urlToImage == null || urlToImage.isEmpty()) {
                throw new NotFoundException("У данного объявления нет картинки");
            }
            try {
                Path fullPathToImageOfGoods = imageService.getFullPathToImageOfGoods(urlToImage);
                return imageService.imageToByteArray(fullPathToImageOfGoods);
            } catch (NoSuchFileException exception) {
                throw new NotFoundException("Файл с картинкой не найден по данному адресу" + urlToImage);
            } catch (IOException exception) {
                throw new IOException("Ошибка при чтении файла ", exception);
            }
        }
    }

}
