package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final CommentService commentService;
    private final CommentsRepository commentsRepository;


    @Override
    public AdsDto createAd(String userLogin, MultipartFile multipartFile, CreateOrUpdateAd createAd) {

        Optional<User> user = userService.getUserByLogin(userLogin);

        if (user.isEmpty()) {
            throw new RuntimeException("NOT FOUND");
        }


        Ad ad = new Ad();
        ad = AdMapper.INSTANCE.createOrUpdateAdToAd(createAd, ad);

        ad.setAuthor(user.get());
        adsRepository.save(ad);

        Image image;

        try {
            image = imageService.changeAdImage(ad.getId(), -1L, multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ad.setImage(image);
        adsRepository.save(ad);

        return AdMapper.INSTANCE.adsToDto(ad);

    }

    @Override
    public List<AdsDto> getAllAds() {

        List<AdsDto> adsDtoList = new ArrayList<>();
        List<Ad> adList = adsRepository.findAll();

        if (adList.isEmpty()) {
            return adsDtoList;
        }

        for (Ad ad : adList) {
            adsDtoList.add(AdMapper.INSTANCE.adsToDto(ad));
        }

        return adsDtoList;

    }

    @Override
    public Optional<ExtendedAdDto> getExtendedAdDto(Long id) {

        Optional<Ad> optionalAd = adsRepository.findById(id);
        return optionalAd.map(AdMapper.INSTANCE::extendedAdToDto);

    }

    @Override
    public boolean deleteByIdAd(String userLogin, Long id) {

        Optional<User> optionalUser = userService.getUserByLogin(userLogin);

        if (optionalUser.isEmpty()) {
            return false;
        }

        if (!adsRepository.existsById(id)) {
            return false;
        } else {

            adsRepository.deleteById(id);
            return true;

        }

    }

    @Override
    public Optional<AdsDto> updateAd(Long id, CreateOrUpdateAd updateAd) {

        Optional<Ad> optionalAd = adsRepository.findById(id);

        if (optionalAd.isEmpty()) {
            return Optional.empty();
        }

        Ad ad = optionalAd.get();
        ad = AdMapper.INSTANCE.createOrUpdateAdToAd(updateAd, ad);

        adsRepository.save(ad);
        return Optional.ofNullable(AdMapper.INSTANCE.INSTANCE.adsToDto(ad));

    }

    @Override
    public List<AdsDto> getMyAds(String userLogin) {

        Optional<User> optionalUser = userService.getUserByLogin(userLogin);
        List<Ad> adList;
        List<AdsDto> adsDtoList = new ArrayList<>();

        if (optionalUser.isEmpty()) {
            return new ArrayList<>();
        }

        adList = optionalUser.get().getUserAds();

        for (Ad ad : adList) {
            adsDtoList.add(AdMapper.INSTANCE.adsToDto(ad));
        }

        return adsDtoList;

    }

    @Override
    public Optional<String> changeImage(Long idAd, MultipartFile multipartFile) {

        Optional<Ad> optionalAd = adsRepository.findById(idAd);

        if (optionalAd.isEmpty()) {
            return Optional.empty();
        }

        Ad ad = optionalAd.get();
        Image image;

        try {

            image = imageService.changeAdImage(idAd, ad.getImage().getId(), multipartFile);
            ad.setImage(image);
            adsRepository.save(ad);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(image.getFileName());

    }

    @Override
    public Optional<Ad> getAdById(Long id) {
        return adsRepository.findById(id);
    }

}
