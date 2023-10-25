package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.AdsService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final UsersRepository usersRepository;

    public AdsServiceImpl(AdsRepository adsRepository, UsersRepository usersRepository) {
        this.adsRepository = adsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Ads getAllAds() {

        List<Ad> adList = adsRepository.findAllAds();

        List<AdDTO> adDTOList = adList.stream()
                .map(AdDTO::fromAd)
                .collect(Collectors.toList());

        return new Ads(adDTOList);
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAd createAd, MultipartFile image, String username) {
        Users user = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        Ad ad = createAd.toAd(user);

        File file;
        try {
            file = image.getResource().getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String filePath = file.getPath();

        ad.setImage(filePath);
        adsRepository.save(ad);

        return AdDTO.fromAd(ad);
    }

    @Override
    public ExtendedAd getAds(int id, String username) {
        ExtendedAd extendedAd = new ExtendedAd();
        extendedAd.setPk(1);
        extendedAd.setAuthorFirstName("Ivan");
        extendedAd.setAuthorLastName("Ivanov");
        extendedAd.setTitle("Заголовок");
        extendedAd.setPrice(15000);
        return extendedAd;
    }

    @Override
    public void removeAd(int id, String username) {

    }

    @Override
    public AdDTO updateAds(int id, CreateOrUpdateAd updateAd, String username) {
        AdDTO dto = new AdDTO();
        dto.setPk(1);
        dto.setAuthor(1);
        dto.setPrice(20000);
        dto.setTitle("Заголовок");
        return dto;
    }

    @Override
    public Ads getAdsMe(String username) {
        AdDTO dto = new AdDTO();
        dto.setPk(1);
        dto.setAuthor(1);
        dto.setPrice(10000);
        dto.setTitle("Заголовок");
        return new Ads(List.of(dto));
    }

    @Override
    public String updateImage(int id, MultipartFile image, String username) {
        return null;
    }
}