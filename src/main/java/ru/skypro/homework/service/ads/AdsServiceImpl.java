package ru.skypro.homework.service.ads;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.custom_exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.projection.AdView;
import ru.skypro.homework.projection.Ads;
import ru.skypro.homework.projection.CreateOrUpdateAd;
import ru.skypro.homework.projection.ExtendedAd;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.image.ImageService;
import ru.skypro.homework.service.user.UserService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class AdsServiceImpl implements AdsService{

    private final AdRepository adRepository;
    private final UserService userService;
    private final ImageService imageService;
    @Override
    public Ads getAllAds() {
        List<AdDTO> tempList = adRepository.findAll()
                .stream()
                .map(AdMapper::fromAd)
                .collect(Collectors.toList());
        return new Ads()
                .setCount(tempList.size())
                .setResults(tempList);
    }

    @Override
    @SneakyThrows
    public AdDTO createAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication) {
        AdDTO ad = new AdDTO()
                .setAuthor(userService.getCurrentUser(authentication))
                .setTitle(properties.getTitle())
                .setPrice(properties.getPrice())
                .setDescription(properties.getDescription())
                .setImage(imageService.createImage(image));
        return AdMapper.fromAd(adRepository.save(AdMapper.toAd(ad)));
    }

    @Override
    public ExtendedAd getAdFullInfo(Integer id) {
        return adRepository.findAllAdFullInfo(id).orElseThrow(AdNotFoundException::new);
    }

    @Override
    public void deleteAd(Integer id) {
        if (!adRepository.existsById(id)){
            throw new AdNotFoundException();
        }
        adRepository.deleteById(id);
    }

    @Override
    public AdView updateAd(Integer id, CreateOrUpdateAd properties) {
        AdDTO adById = AdMapper.fromAd(adRepository.findById(id).orElseThrow(AdNotFoundException::new));
        adById.setTitle(properties.getTitle())
                .setDescription(properties.getDescription())
                .setPrice(properties.getPrice());
        return AdMapper.toView(adRepository.save(AdMapper.toAd(adById)));
    }

    @Override
    public Ads getAllAdsByUser(Authentication authentication) {
        String username = userService.getCurrentUser(authentication).getEmail();
        List<AdDTO> tempList = adRepository.getAllAdsByUser(username)
                .stream()
                .map(AdMapper::fromAd)
                .collect(Collectors.toList());
        return new Ads()
                .setCount(tempList.size())
                .setResults(tempList);
    }

    @Override
    @SneakyThrows
    public String updateImage(Integer id, MultipartFile image) {
        AdDTO adToUpdate = AdMapper.fromAd(adRepository.findById(id).orElseThrow(AdNotFoundException::new));
        adToUpdate.setImage(image.getResource().getURL().toString());
        return adRepository.save(AdMapper.toAd(adToUpdate)).getImage();
    }
}
