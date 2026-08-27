package ru.skypro.homework.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;

    public List<Ad> getAllAds() {
        return adRepository.findAll().stream()
                .map(adMapper::toAdDto)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Optional<Ad> createAd(CreateOrUpdateAd createDto, MultipartFile image, Integer authorId) throws IOException {
        return userRepository.findById(authorId)
                .flatMap(author -> adMapper.toEntity(createDto)
                        .map(entity -> {
                            entity.setAuthor(author);
                            if (image != null && !image.isEmpty()) {
                                try {
                                    ImageEntity savedImage = imageService.saveImage(image);
                                    entity.setImage(savedImage);
                                } catch (IOException e) {
                                    throw new RuntimeException("Failed to save image", e);
                                }
                            }
                            return adRepository.save(entity);
                        }))
                .flatMap(adMapper::toAdDto);
    }

    public Optional<ExtendedAd> getAdById(Integer id) {
        return adRepository.findById(id)
                .flatMap(adMapper::toExtendedAdDto);
    }


    private boolean hasAdminRole(Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

    private Integer getUserId(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .map(UserEntity::getId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Optional<Ad> updateAd(Integer id, CreateOrUpdateAd updateDto, Authentication authentication) {
        return adRepository.findById(id)
                .map(entity -> {
                    if (!hasAdminRole(authentication) && !entity.getAuthor().getId().equals(getUserId(authentication))) {
                        throw new AccessDeniedException("You are not the author of this ad");
                    }
                    entity.setTitle(updateDto.getTitle());
                    entity.setPrice(updateDto.getPrice());
                    entity.setDescription(updateDto.getDescription());
                    return adRepository.save(entity);
                })
                .flatMap(adMapper::toAdDto);

    }

    public boolean deleteAd(Integer id, Authentication authentication) {
        return adRepository.findById(id)
                .map(entity -> {
                    if (!hasAdminRole(authentication) && !entity.getAuthor().getId().equals(getUserId(authentication))) {
                        throw new AccessDeniedException("You are not the author of this ad");
                    }
                    adRepository.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    public List<Ad> getAdsByUser(Integer userId) {
        return adRepository.findByAuthorId(userId).stream()
                .map(adMapper::toAdDto)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void updateAdImage(Integer adId, MultipartFile file, Authentication authentication) throws IOException {
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        if (!hasAdminRole(authentication) && !ad.getAuthor().getId().equals(getUserId(authentication))) {
            throw new AccessDeniedException("You are not the author of this ad");
        }

        ImageEntity image = imageService.saveImage(file);

        if (ad.getImage() != null) {
            imageService.deleteImage(ad.getImage().getId());
        }

        ad.setImage(image);
        adRepository.save(ad);
    }
}
