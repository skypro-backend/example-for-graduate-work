package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;


    @Transactional(readOnly = true)
    public List<AdDto> getAllAds() {
        List<AdEntity> ads = adRepository.findAllByOrderByCreatedAtDesc();
        return adMapper.toAdDtoList(ads);
    }


    @Transactional(readOnly = true)
    public ExtendedAdDto getAdById(Integer id) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление с id " + id + " не найдено"));
        return adMapper.toExtendedAdDto(entity);
    }


    @Transactional(readOnly = true)
    public List<AdDto> getMyAds(String email) {
        List<AdEntity> ads = adRepository.findByAuthorEmailIgnoreCase(email);
        return adMapper.toAdDtoList(ads);
    }


    public AdDto createAd(CreateOrUpdateAdDto dto, UserEntity author) {
        AdEntity entity = adMapper.toEntity(dto);
        entity.setAuthor(author);
        entity.setCreatedAt(LocalDateTime.now());
        entity = adRepository.save(entity);
        log.info("Создано объявление с id {} пользователем {}", entity.getId(), author.getEmail());
        return adMapper.toAdDto(entity);
    }


    public AdDto updateAd(Integer id, CreateOrUpdateAdDto dto) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление с id " + id + " не найдено"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !entity.getAuthor().getEmail().equalsIgnoreCase(currentEmail)) {
            throw new ForbiddenException("Нет прав на редактирование чужого объявления");
        }

        adMapper.updateEntity(entity, dto);
        entity = adRepository.save(entity);
        log.info("Обновлено объявление с id {}", id);
        return adMapper.toAdDto(entity);
    }

    public void deleteAd(Integer id) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление с id " + id + " не найдено"));


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !entity.getAuthor().getEmail().equalsIgnoreCase(currentEmail)) {
            throw new ForbiddenException("Нет прав на удаление чужого объявления");
        }

        adRepository.delete(entity);
        log.info("Удалено объявление с id {} пользователем {}", id, currentEmail);
    }
}