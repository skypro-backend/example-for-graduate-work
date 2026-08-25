package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdEntity;
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


    public List<Ad> getAllAds() {
        return adRepository.findAll().stream()
                .map(adMapper::toAdDto)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Optional<Ad> createAd(CreateOrUpdateAd createDto, MultipartFile image, Integer authorId) {
        return userRepository.findById(authorId)
                .flatMap(author -> adMapper.toEntity(createDto)
                        .map(entity -> {
                            entity.setAuthor(author);
                            return adRepository.save(entity);
                        }))
                .flatMap(adMapper::toAdDto);
    }

    public Optional<ExtendedAd> getAdById(Integer id) {
        return adRepository.findById(id)
                .flatMap(adMapper::toExtendedAdDto);
    }

    public Optional<Ad> updateAd(Integer id, CreateOrUpdateAd updateDto, Integer currentUserId) {
        return adRepository.findById(id)
                .filter(entity -> {
                    if (entity.getAuthor().getId().equals(currentUserId)) {
                        return true;
                    }
                    throw new AccessDeniedException("You are not the author of this ad");
                })
                .map(entity -> {
                    entity.setTitle(updateDto.getTitle());
                    entity.setPrice(updateDto.getPrice());
                    entity.setDescription(updateDto.getDescription());
                    return adRepository.save(entity);
                })
                .flatMap(adMapper::toAdDto);
    }

    public boolean deleteAd(Integer id, Integer currentUserId) {
        return adRepository.findById(id)
                .map(entity -> {
                    if (!entity.getAuthor().getId().equals(currentUserId)) {
                        throw new AccessDeniedException("You are not the author of this ad");
                    }
                    adRepository.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    public List<Ad> getAdsByUser(Integer userId) {
        return userRepository.findById(userId)
                .map(user -> adRepository.findAll().stream()
                        .filter(ad -> ad.getAuthor().getId().equals(userId))
                        .map(adMapper::toAdDto)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

}
