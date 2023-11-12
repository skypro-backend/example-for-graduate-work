package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.Image;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.AuthProvider;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdServiceImpl implements AdService {

    private final AdRepository repository;
    private final AdMapper mapper;
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final AuthProvider authProvider;

    @Override
    public AdsDto getAllAds() {
        List<AdDto> adsDto = repository.findAll().stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
        return AdsDto.builder()
                .count(adsDto.size())
                .results(adsDto)
                .build();
    }

    @Transactional
    @Override
    public AdDto addAd(MultipartFile file, CreateOrUpdateAdDto dto) {
        String imagePath = imageService.createImagePath(file);
        imageService.upload(imagePath, file);
        Ad ad = mapper.convertToEntity(dto);
        ad.setUser(userRepository.findAuthUserByEmail(authProvider.getUsername()));
        ad.setImage(imageService.save(Image.builder().imagePath(imagePath).build()));
        return mapper.convertToDto(repository.save(ad));
    }

    @Override
    public ExtendedAdDto getAd(Integer id) {
        return repository.findById(id)
                .map(mapper::convertToExtendedDto)
                .orElseThrow(AdNotFoundException::new);
    }

    @Transactional
    @Override
    public void removeAd(Integer id) {
        repository.findById(id)
                .ifPresentOrElse(ad -> {
                    Image image = ad.getImage();
                    repository.deleteById(ad.getId());
                    imageService.delete(image);
                }, () -> {
                    throw new AdNotFoundException();
                });
    }

    @Transactional
    @Override
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto dto) {
        return repository.findById(id)
                .map(ad -> {
                    if (dto.getDescription() != null) {
                        ad.setDescription(dto.getDescription());
                    }
                    if (dto.getPrice() != null) {
                        ad.setPrice(dto.getPrice());
                    }
                    if (dto.getTitle() != null) {
                        ad.setTitle(dto.getTitle());
                    }
                    return mapper.convertToDto(ad);
                })
                .orElseThrow(AdNotFoundException::new);
    }

    @Override
    public AdsDto getAdsMe() {
        List<AdDto> adsDto = repository.findByUserEmail(authProvider.getUsername()).stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
        return AdsDto.builder()
                .count(adsDto.size())
                .results(adsDto)
                .build();
    }

    @Transactional
    @Override
    public byte[] updateAdImage(Integer id, MultipartFile file) {
        return repository.findById(id)
                .map(ad -> {
                    Image image = ad.getImage();
                    imageService.update(image, file);
                    return imageService.download(image.getImagePath());
                })
                .orElseThrow(AdNotFoundException::new);
    }

    @Override
    public byte[] getImage(Integer id) {
        return repository.findById(id)
                .flatMap(ad -> Optional.ofNullable(ad.getImage()))
                .map(image -> imageService.download(image.getImagePath()))
                .orElse(null);
    }

    @Override
    public boolean existByAdIdAndUsername(Integer id, String username) {
        return repository.existsByIdAndUserEmail(id, username);
    }

}
