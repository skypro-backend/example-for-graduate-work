package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.mapper.AdvertMapper;
import ru.skypro.homework.model.Advert;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdvertRepository;
import ru.skypro.homework.service.AdvertService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class AdvertServiceImpl implements AdvertService {
    private final UserService userService;
    private final ImageService imageService;
    private final AdvertRepository repository;
    private final AdvertMapper mapper;


    /**
     * getUser() is a method used to get the current user
     * @author radyushinaalena
     */
    private User getUser() {
        return userService.find();
    }


    /**
     * find(int id) is a public method used to find an ad
     * @author radyushinaalena
     */
    @Override
    public Advert find(int id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    /**
     * createAdvert(CreateOrUpdateAdDto dto, MultipartFile image) is a public method used to create an ad
     * @author radyushinaalena
     */
    @Override
    public AdDto createAdvert(CreateOrUpdateAdDto dto, MultipartFile image) {
        var advert = mapper.toAdvert(dto);
        var user = getUser();
        String filename;
        try {
            filename = imageService.create(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        advert.setImage("/images/" + filename);
        advert.setAuthor(user);
        repository.save(advert);
        return mapper.advertToAdvertDto(advert);
    }


    /**
     * getAdvertById(int id) is a public method used to read an ad
     * @author radyushinaalena
     */
    @Override
    public ExtendedAdDto getAdvertById(int id) {
        var advert = find(id);
        return mapper.toExtendedDto(advert);
    }


    /**
     * getAdvert() is a public method used to read all the author's ads
     * @author radyushinaalena
     */
    @Override
    public AdsDto getAdvert() {
        var authorId = getUser().getId();
        var advertList = repository.findByAuthorId(authorId);
        var advertDtoList = mapper.advertsToAdvertDtos(advertList);
        return new AdsDto(advertDtoList);
    }


    /**
     * getAllAdverts() is a public method used to read all the ads of all the authors
     * @author radyushinaalena
     */
    @Override
    public AdsDto getAllAdverts() {
        var advertList = repository.findAll();
        var advertDtoList = mapper.advertsToAdvertDtos(advertList);
        return new AdsDto(advertDtoList);
    }


    /**
     * updateAdvert(int id, CreateOrUpdateAdDto dto) is a public method used to update an ad
     * @author radyushinaalena
     */
    @Override
    public AdDto updateAdvert(int id, CreateOrUpdateAdDto dto) {
        var advert = find(id);
        mapper.updateAdFromDto(dto, advert);
        repository.save(advert);
        return mapper.advertToAdvertDto(advert);
    }


    /**
     * update(int id, MultipartFile image) is a public method used to update the ad image
     * @author radyushinaalena
     */
    @Override
    public String update(int id, MultipartFile image) {
        var advert = find(id);
        String filename;
        try {
            filename = imageService.create(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        advert.setImage("/images/" + filename);
        repository.save(advert);
        return filename;
    }


    /**
     * deleteAdvert(int id) is a public method used to remove an ad
     * @author radyushinaalena
     */
    @Override
    public void deleteAdvert(int id) {
        var advert = find(id);
        repository.delete(advert);
    }
}
