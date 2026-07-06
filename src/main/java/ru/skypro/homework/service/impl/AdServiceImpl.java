package ru.skypro.homework.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.ad.Ad;
import ru.skypro.homework.ad.Ads;
import ru.skypro.homework.ad.CreateOrUpdateAd;
import ru.skypro.homework.ad.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.IAdService;
import org.springframework.core.env.Environment;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdServiceImpl implements IAdService {

    private final AdRepository adRepo;
    private final UserRepository userRepo;
    private final AdMapper adMapper;

    private final Environment env;

    public AdServiceImpl(
            AdRepository adRepo,
            UserRepository userRepo,
            AdMapper adMapper,
            Environment env) {

        this.adRepo = adRepo;
        this.userRepo = userRepo;
        this.adMapper = adMapper;
        this.env = env;
    }

    @Override
    public Ads getAllAds(int offset, int limit) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Page<AdEntity> page = adRepo.findAll(PageRequest.of(offset, limit, sort));
        return mapToAds(page);
    }

    @Override
    public Optional<ExtendedAd> getAdById(long id) {
        return adRepo.findById(id)
                .map(adMapper::entityToExtendedDto);
    }

    @Override
    public Ads getMyAds(Authentication auth) {
        long userId = getCurrentUserId(auth);
        Page<AdEntity> page = adRepo.findByAuthorId(userId, PageRequest.of(0, 10));
        return mapToAds(page);
    }

    @Override
    public Ad createAd(CreateOrUpdateAd data, Authentication auth) {
        AdEntity entity = adMapper.dtoToEntity(data);
        entity.setAuthor(getCurrentUser(auth));
        return adMapper.entityToDto(adRepo.save(entity));
    }

    @Override
    public void updateImage(long id, MultipartFile image, Authentication auth) {
        AdEntity ad = adRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));

        checkOwnership(ad, auth);
        String filename = saveImage(image);
        ad.setImageUrl(filename);
        adRepo.save(ad);
    }
    private String saveImage(MultipartFile file) {
        try {

            byte[] bytes = file.getBytes();

            String originalName = file.getOriginalFilename();
            String extension = Objects.requireNonNull(originalName).substring(originalName.lastIndexOf('.')).toLowerCase();
            String uuid = UUID.randomUUID().toString();
            String filename = uuid + extension;

            Path root = Paths.get(env.getProperty("app.images.root-path"));
            Files.createDirectories(root);
            Files.write(root.resolve(filename), bytes);

            return env.getProperty("app.images.url-prefix") + filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image", e);
        }
    }

    @Override
    public Ad updateAd(long id, CreateOrUpdateAd data, Authentication auth) {
        AdEntity existing = adRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));

        checkOwnership(existing, auth);

        adMapper.updateFromDto(data, existing);
        return adMapper.entityToDto(adRepo.save(existing));
    }

    @Override
    public void deleteAd(long id, Authentication auth) {
        AdEntity existing = adRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));

        checkOwnership(existing, auth);
        adRepo.delete(existing);
    }

    private Ads mapToAds(Page<AdEntity> page) {
        Ads result = new Ads();
        result.setCount((int) page.getTotalElements());
        result.setResults(page.map(adMapper::entityToDto).toList());
        return result;
    }

    private UserEntity getCurrentUser(Authentication auth) {
        return userRepo.findByUsername(auth.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private long getCurrentUserId(Authentication auth) {
        return getCurrentUser(auth).getId();
    }

    private void checkOwnership(AdEntity ad, Authentication auth) {
        UserEntity current = getCurrentUser(auth);

        if (!(auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) &&
                !ad.getAuthor().getId().equals(current.getId())) {
            throw new AccessDeniedException("Forbidden");
        }
    }
}