package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.AdsDto;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAdDto;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    private final ImageService imageService;

    @Override
    public AdsDto getAllAds() {
        List<AdDto> ads = adRepository.findAll().stream()
                .map(adMapper::toDto)
                .toList();
        return new AdsDto(ads.size(), ads);
    }

    @Override
    @Transactional
    public AdDto addAd(String email, CreateOrUpdateAdDto dto, MultipartFile image) {
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Ad ad = adMapper.toEntity(dto);
        ad.setAuthor(author);

        String imagePath = imageService.saveImage(image);
        ad.setImage(imagePath);
        Ad savedAd = adRepository.save(ad);
        return adMapper.toDto(savedAd);
    }

    @Override
    public ExtendedAdDto getAd(Integer id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Объявление не найдено"));
        return adMapper.toExtendedDto(ad);
    }

    @Override
    @Transactional
    public void removeAd(String email, Integer id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Объявление не найдено"));
        if(!ad.getAuthor().getEmail().equals(email)){
            throw new ForbiddenException("У вас нет прав на удаление этого объявления");
        }
        adRepository.delete(ad);
    }

    @Override
    @Transactional
    public AdDto updateAd(String email, Integer id, CreateOrUpdateAdDto dto) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Объявление не найдено"));
        if(!ad.getAuthor().getEmail().equals(email)){
            throw new ForbiddenException("У вас нет прав на изменение данного объявления");
        }
        ad.setTitle(dto.title());
        ad.setPrice(dto.price());
        ad.setDescription(dto.description());

        Ad savedAd = adRepository.save(ad);
        return adMapper.toDto(savedAd);
    }

    @Override
    public AdsDto getAdsMe(String email) {
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        List<AdDto> ads = adRepository.findAllByAuthorId(author.getId())
                .stream()
                .map(adMapper::toDto)
                .toList();
        return new AdsDto(ads.size(), ads);
    }

    @Override
    @Transactional
    public byte[] updateAdImage(String email, Integer id, MultipartFile image) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Объявление не найдено"));
        if(!ad.getAuthor().getEmail().equals(email)){
            throw new ForbiddenException("Недостаточно прав для изменения объявления");
        }

        String imagePath = imageService.saveImage(image);
        ad.setImage(imagePath);
        adRepository.save(ad);
        return imageService.getImage(imagePath.substring("/images/".length()));
    }
}
