package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.Exceptions.FindNoEntityException;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.*;
import ru.skypro.homework.service.*;
import ru.skypro.homework.mapper.*;
import ru.skypro.homework.repository.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdMapper mapper;


    @Override
    public AdDto add(CreateOrUpdateAdDto properties, MultipartFile image, String email) throws IOException {
        Ad ad = mapper.createOrUpdateAdToEntity(properties, userService.getEntity(email));
        ad.setImage(imageService.saveImage(image));
        return mapper.entityToAdDto(adRepository.save(ad));
    }

    @Override
    public ExtendedAdDto getFullAdsById(int id) {
        return mapper.entityToExtendedAdDto(getEntity(id));
    }

    @Override
    public void delete(int id) throws IOException {
        Image imageEntity = getEntity(id).getImage();
        adRepository.deleteById(id);
        imageService.deleteImage(imageEntity);
    }


    @Override
    public AdDto update(int id, CreateOrUpdateAdDto ads) {
        Ad entity = getEntity(id);
        entity.setTitle(ads.getTitle());
        entity.setDescription(ads.getDescription());
        entity.setPrice(ads.getPrice());
        adRepository.save(entity);
        return mapper.entityToAdDto(entity);
    }

    @Override
    public Ad getEntity(int id) {
        return adRepository.findById(id).orElseThrow(() -> new FindNoEntityException("объявление"));
    }

    @Override
    public void uploadImage(int id, MultipartFile image) throws IOException {
        Ad adEntity = getEntity(id);
        Image imageEntity = adEntity.getImage();
        adEntity.setImage(imageService.saveImage(image));
        adRepository.save(adEntity);
        if (imageEntity != null) {
            imageService.deleteImage(imageEntity);
        }
    }


    @Override
    public AdsDto getAllAds() {
        return getWrapper(adRepository.findAll());
    }

    @Override
    public AdsDto getAllMyAds(String name) {
        return getWrapper(adRepository.findAllByAuthorUsername(name));
    }

    private AdsDto getWrapper(List<Ad> list) {
        List<AdDto> result = new LinkedList<>();
        list.forEach((entity -> result.add(mapper.entityToAdDto(entity))));
        return new AdsDto(result.size(), result);
    }

}