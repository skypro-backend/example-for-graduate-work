package ru.skypro.homework.service.ads.impl;

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
import ru.skypro.homework.service.users.impl.UserService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdMapper adMapper;

    private final AdsRepository adsRepository;

    private final ImageService imageService;

    public AdsServiceImpl(AdMapper adMapper, AdsRepository adsRepository, ImageService imageService) {
        this.adMapper = adMapper;
        this.adsRepository = adsRepository;
        this.imageService = imageService;
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
    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) {
        User author = UserService.getAuthor();
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
    public ExtendedAdDto getAds(Integer id) {
        User author = UserService.getAuthor();
        Optional<Ad> adOptional = adsRepository.findByAuthorAndPk(author, id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Объявление с таким id не найдено: " + id);
        } else {
            Ad ad = adOptional.get();
            return adMapper.toExtendedAdDto(ad);
        }
    }

    @Override
    @Transactional
    public void removeAd(Integer id) {
        User author = UserService.getAuthor();
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
    public AdDto updateAds(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        User author = UserService.getAuthor();
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
    public AdsDto getAdsMe() {
        User author = UserService.getAuthor();
        List<Ad> adsList = adsRepository.findAllByAuthor(author);
        return adMapper.toAdsDto(adsList);
    }

    @Override
    public byte[] updateImage(Integer id, MultipartFile image) throws IOException {
        Optional<Ad> adOptional = adsRepository.findByPkIs(id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Объявление с таким id не найдено: " + id);
        } else {
            Ad ad = adOptional.get();
            String urlToImage = ad.getImage();
            imageService.deleteImageOfGoods(urlToImage);
            String urlToImageOfGoods = imageService.consumeImageOfGoods(image);
            Path fullPathToImageOfGoods = imageService.getFullPathToImageOfGoods(urlToImageOfGoods);
            return imageService.imageToByteArray(fullPathToImageOfGoods);
        }
    }

    @Override
    public byte[] getImage(Integer id) throws IOException {
        Optional<Ad> adOptional = adsRepository.findByPkIs(id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Объявление с таким id{} не найдено: " + id);
        } else {
            String urlToImage = adOptional.get().getImage();
            Path fullPathToImageOfGoods = imageService.getFullPathToImageOfGoods(urlToImage);
            return imageService.imageToByteArray(fullPathToImageOfGoods);
        }
    }

}
