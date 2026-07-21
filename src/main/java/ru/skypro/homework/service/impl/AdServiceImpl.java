package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.exception.AccessDeniedException;
import ru.skypro.homework.exception.ResourceNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    @Override
    public Ads getAllAds() {

        List<ru.skypro.homework.entity.Ad> ads =
                adRepository.findAll();

        Ads result = new Ads();

        result.setCount(ads.size());

        result.setResults(
                ads.stream()
                        .map(adMapper::toDto)
                        .toList()
        );

        return result;
    }

    @Override
    public ru.skypro.homework.dto.Ad createAd(
            CreateOrUpdateAd dto,
            MultipartFile image
    ) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        ru.skypro.homework.entity.User user =
                userRepository.findByEmail(email)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "User not found"
                                )
                        );

        ru.skypro.homework.entity.Ad ad =
                new ru.skypro.homework.entity.Ad();

        ad.setTitle(dto.getTitle());
        ad.setPrice(dto.getPrice());
        ad.setDescription(dto.getDescription());
        ad.setAuthor(user);

        if (image != null) {
            ad.setImage(
                    image.getOriginalFilename()
            );
        }

        return adMapper.toDto(
                adRepository.save(ad)
        );
    }

    @Override
    public ExtendedAd getAd(Integer id) {

        ru.skypro.homework.entity.Ad ad =
                adRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Ad not found"
                                )
                        );

        ExtendedAd result = new ExtendedAd();

        result.setPk(ad.getId());
        result.setTitle(ad.getTitle());
        result.setPrice(ad.getPrice());
        result.setDescription(ad.getDescription());
        result.setImage(ad.getImage());

        if (ad.getAuthor() != null) {

            result.setEmail(
                    ad.getAuthor().getEmail()
            );

            result.setAuthorFirstName(
                    ad.getAuthor().getFirstName()
            );

            result.setAuthorLastName(
                    ad.getAuthor().getLastName()
            );

            result.setPhone(
                    ad.getAuthor().getPhone()
            );
        }

        return result;
    }

    @Override
    public void deleteAd(Integer id) {

        ru.skypro.homework.entity.Ad ad =
                adRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Ad not found"
                                )
                        );

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        if (!ad.getAuthor().getEmail().equals(email)) {

            throw new AccessDeniedException(
                    "You are not the owner"
            );
        }

        adRepository.delete(ad);
    }

    @Override
    public ru.skypro.homework.dto.Ad updateAd(
            Integer id,
            CreateOrUpdateAd dto
    ) {

        ru.skypro.homework.entity.Ad ad =
                adRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Ad not found"
                                )
                        );

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        if (!ad.getAuthor().getEmail().equals(email)) {

            throw new AccessDeniedException(
                    "You are not the owner"
            );
        }

        ad.setTitle(dto.getTitle());
        ad.setPrice(dto.getPrice());
        ad.setDescription(dto.getDescription());


        return adMapper.toDto(
                adRepository.save(ad)
        );
    }

    @Override
    public Ads getMyAds() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        ru.skypro.homework.entity.User user =
                userRepository.findByEmail(email)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "User not found"
                                )
                        );

        List<ru.skypro.homework.entity.Ad> ads =
                adRepository.findByAuthorId(
                        user.getId()
                );

        Ads result = new Ads();

        result.setCount(ads.size());

        result.setResults(
                ads.stream()
                        .map(adMapper::toDto)
                        .toList()
        );

        return result;
    }

    @Override
    public void updateImage(
            Integer id,
            MultipartFile image
    ) {
        ru.skypro.homework.entity.Ad ad =
                adRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Ad not found"
                                )
                        );

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        if (!ad.getAuthor().getEmail().equals(email)) {

            throw new AccessDeniedException(
                    "You are not the owner"
            );
        }

        ad.setImage(
                image.getOriginalFilename()
        );

        adRepository.save(ad);
    }
}