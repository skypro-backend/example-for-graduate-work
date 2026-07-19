package ru.skypro.homework.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.AdRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;

    public AdService(AdRepository adRepository, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
    }

    public List<AdDTO> getAllAds() {
        return adRepository.findAll().stream()
                .map(adMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AdDTO createAd(AdDTO adDTO, Users author) {
        Ad adEntity = adMapper.toEntity(adDTO);
        adEntity.setAuthor(author);

        Ad savedAd = adRepository.save(adEntity);
        return adMapper.toDTO(savedAd);
    }

    public AdDTO updateAd(Long id, CreateOrUpdateAdDTO adDTO, Users currentUser) {
        Ad existingAd = adRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Ad not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        if (!existingAd.getAuthor().getUsername().equals(currentUsername)) {
            throw new AccessDeniedException("You can only update your own ads");
        }

        adMapper.updateEntityFromDTO(adDTO,existingAd);
        Ad updatedAd = adRepository.save(existingAd);

        return adMapper.toDTO(updatedAd);
    }

    public void deleteAd(Long id, Users currentUser) {
        Ad ad = adRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Ad not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        if (!ad.getAuthor().getUsername().equals(currentUsername)) {
            throw new AccessDeniedException("You can only delete your own ads");
        }

        adRepository.delete(ad);
    }
}
