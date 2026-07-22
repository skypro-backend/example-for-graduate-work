package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.constants.ErrorMessages;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AccessDeniedException;
import ru.skypro.homework.exception.ResourceNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.image.ImageStorageService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final ImageStorageService imageStorageService;

    @Override
    public Ads getAllAds() {

        List<Ad> ads =
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

        User user =
                userRepository.findByEmail(getCurrentUserEmail())
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        ErrorMessages.USER_NOT_FOUND
                                )
                        );

        Ad ad = adMapper.toEntity(dto);

        ad.setAuthor(user);

        if (image != null && !image.isEmpty()) {

            try {

                ad.setImage(
                        imageStorageService.saveAdImage(image)
                );

            } catch (Exception e) {

                throw new RuntimeException(
                        ErrorMessages.IMAGE_SAVE_ERROR
                );
            }
        }

        return adMapper.toDto(
                adRepository.save(ad)
        );
    }

    @Override
    public ExtendedAd getAd(Integer id) {

        Ad ad = getAdById(id);
        return adMapper.toExtendedDto(ad);
    }

    @Override
    public void deleteAd(Integer id) {

        Ad ad = getAdById(id);

        checkOwner(ad);

        adRepository.delete(ad);
    }

    @Override
    public ru.skypro.homework.dto.Ad updateAd(
            Integer id,
            CreateOrUpdateAd dto
    ) {

        Ad ad = getAdById(id);

        checkOwner(ad);

        adMapper.updateEntityFromDto(
                dto,
                ad
        );

        return adMapper.toDto(
                adRepository.save(ad)
        );
    }

    @Override
    public Ads getMyAds() {

        User user =
                userRepository.findByEmail(getCurrentUserEmail())
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        ErrorMessages.USER_NOT_FOUND
                                )
                        );

        List<Ad> ads =
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

        Ad ad = getAdById(id);

        checkOwner(ad);

        try {

            ad.setImage(
                    imageStorageService.saveAdImage(image)
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    ErrorMessages.IMAGE_SAVE_ERROR
            );
        }

        adRepository.save(ad);
    }

    private Ad getAdById(Integer id) {

        return adRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                ErrorMessages.AD_NOT_FOUND
                        )
                );
    }

    private boolean isAdmin(Authentication authentication) {

        return authentication.getAuthorities()
                .stream()
                .anyMatch(
                        authority ->
                                authority.getAuthority()
                                        .equals("ROLE_ADMIN")
                );
    }

    private void checkOwner(Ad ad) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();


        if (isAdmin(authentication)) {
            return;
        }


        if (ad.getAuthor() == null ||
                !ad.getAuthor()
                        .getEmail()
                        .equals(authentication.getName())) {

            throw new AccessDeniedException(
                    ErrorMessages.YOU_ARE_NOT_THE_OWNER
            );
        }
    }

    private String getCurrentUserEmail() {

        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}