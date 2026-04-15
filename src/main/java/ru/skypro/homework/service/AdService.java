package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    @Transactional(readOnly = true)
    public List<AdDto> getAllAds() {
        return adRepository.findAll().stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    private AdDto createdAd(CreateOrUpdateAdDto createOrUpdateAdDto, String email, String image) {
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Ad ad = new Ad();
        adMapper.updateEntityFromDto(createOrUpdateAdDto, ad);
        ad.setAuthor((author));
        ad.setImage(image);
        return adMapper.toDto(adRepository.save(ad));
    }

    @Transactional(readOnly = true)
    public ExtendedAdDto getAdById(Integer id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявлелние не найдено"));
        return adMapper.toExtendedAdDto(ad);
    }

    @Transactional
    public void deleteAd(Integer id, String emal) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявлелние не найдено"));
        if (!ad.getAuthor().getEmail().equals(emal)) {
            throw new RuntimeException("Запрещено");
        }
        adRepository.deleteById(id);
    }

    @Transactional
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, String email) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявлелние не найдено"));
        if (!ad.getAuthor().getEmail().equals(email)) {
            throw new RuntimeException("Запрещено");
        }
        adMapper.updateEntityFromDto(createOrUpdateAdDto, ad);
        return adMapper.toDto(adRepository.save(ad));
    }

    @Transactional(readOnly = true)
    public List<AdDto> getMyAds(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return adRepository.findByAuthor(user).stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    private void updateAdImade(Integer id, String image, String email) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));
        if (!ad.getAuthor().getEmail().equals(email)) {
            throw new RuntimeException("Запрещено");
        }
        ad.setImage(image);
        adRepository.save(ad);
    }
}
