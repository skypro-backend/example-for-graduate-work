package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private static final String IMAGE_DIR = "src/main/resources/images/ad/";
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    @Override
    public Ads getAllAds() {
        List<Ad> ads = adRepository.findAll().stream().map(adMapper::toDto).collect(Collectors.toList());
        Ads result = new Ads();
        result.setCount(ads.size());
        result.setResults(ads);
        return result;
    }

    @Override
    public ExtendedAd getExtendedAd(Integer id) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + id + " не найдено"));
        return adMapper.toExtendedDto(adEntity);
    }

    @Override
    @Transactional
    public Ad createAd(AdEntity adEntity, String email) {
        UserEntity author = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        adEntity.setAuthor(author);
        AdEntity saved = adRepository.save(adEntity);

        return adMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteAd(Integer id, String email) {
        AdEntity ad = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + id + " не найдено"));

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (!ad.getAuthor().getId().equals(user.getId()) && !user.getRole().name().equals("ADMIN")) {
            throw new AccessDeniedException("У вас нет прав на удаление этого объявления");
        }

        adRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Ad updateAd(Integer id, AdEntity updatedAd, String email) {
        AdEntity existing = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + id + " не найдено"));

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (!existing.getAuthor().getId().equals(user.getId()) && !user.getRole().name().equals("ADMIN")) {
            throw new AccessDeniedException("У вас нет прав на редактирование этого объявления");
        }

        existing.setTitle(updatedAd.getTitle());
        existing.setPrice(updatedAd.getPrice());
        existing.setDescription(updatedAd.getDescription());

        AdEntity saved = adRepository.save(existing);
        return adMapper.toDto(saved);
    }

    @Override
    public Ads getAdsByUser(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        List<Ad> ads = adRepository.findByAuthorId(user.getId()).stream().map(adMapper::toDto).collect(Collectors.toList());

        Ads result = new Ads();
        result.setCount(ads.size());
        result.setResults(ads);
        return result;
    }

    @Override
    @Transactional
    public Ad updateImage(Integer id, byte[] image) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + id + " не найдено"));

        Path dir = Paths.get(IMAGE_DIR);
        Path imagePath = dir.resolve(id + ".jpg");

        try {
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Files.write(imagePath, image);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить изображение объявления", e);
        }

        return adMapper.toDto(adEntity);
    }
}