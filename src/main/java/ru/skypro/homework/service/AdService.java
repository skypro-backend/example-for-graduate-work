package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entities.Ad;
import ru.skypro.homework.entities.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.util.ImageUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdService {

    private AdRepository adRepository;
    private UserRepository userRepository;

    public Ad toEntity(ru.skypro.homework.dto.Ad DTO) {
        return Ad.builder().pk(DTO.getPk()).image(DTO.getImage()).title(DTO.getTitle()).price(DTO.getPrice())
                .author(userRepository.getReferenceById(DTO.getAuthor())).build();
    }

    public ru.skypro.homework.dto.Ad toDTO(Ad entity) {
        return ru.skypro.homework.dto.Ad.builder().pk(entity.getPk()).image(entity.getImage())
                .title(entity.getTitle()).price(entity.getPrice()).author(entity.getAuthor().getId()).build();
    }

    public List<ru.skypro.homework.dto.Ad> toDTOList(List<Ad> ads) {
        List<ru.skypro.homework.dto.Ad> DTOAd = new ArrayList<>();
        for (Ad ad : ads) {
            DTOAd.add(toDTO(ad));
        }
        return DTOAd;
    }

    public Ads getAll() {
        List<Ad> ads = adRepository.findAll();
        return new Ads(toDTOList(ads), ads.size());
    }

    public ru.skypro.homework.dto.Ad getAd(Long id) {
        return toDTO(findAdById(id));
    }

    public boolean existAd(Long id) {
        return adRepository.existsById(id);
    }

    public void deleteAd(long id) {
        adRepository.deleteById(id);
    }

    public void addAd(CreateOrUpdateAd ad, MultipartFile image, String userName) {
        adRepository.save(Ad.builder().title(ad.getTitle()).price(ad.getPrice())
                .image(ImageUtil.saveImage(image)).author(userRepository.findByEmail(userName))
                .description(ad.getDescription()).build());
    }

    public boolean postedByUser(long postId, UserDetails user) {
        return adRepository.getReferenceById(postId).getAuthor().equals(findUser(user));
    }

    public void updatePost(long postId, CreateOrUpdateAd ad) {
        ru.skypro.homework.entities.Ad bdAd = adRepository.getReferenceById(postId);
        bdAd.setTitle(ad.getTitle());
        bdAd.setPrice(ad.getPrice());
        bdAd.setDescription(ad.getDescription());
        adRepository.save(bdAd);
    }

    public Ads userAds(UserDetails userDetails) {
        List<Ad> ads = findUser(userDetails).getAds();
        return new Ads(toDTOList(ads), ads.size());
    }

    public void updatePostImage(long postId, MultipartFile image) {
        Ad ad = findAdById(postId);
        ImageUtil.deleteImage(ad.getImage());
        ad.setImage(ImageUtil.saveImage(image));
        adRepository.save(ad);
    }

    private Ad findAdById(long id) {
        return adRepository.getReferenceById(id);
    }

    private User findUser(UserDetails userDetails) {
        return userRepository.findByEmail(userDetails.getUsername());
    }
}
