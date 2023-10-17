package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.AdsDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
      private final AdRepository adRepository;
      private final AdMapper adMapper;
      private final UserRepository userRepository;

      @Override
      public List<AdDto> getAllAds () {
            return adMapper.toAdsDto (adRepository.findAll());
      }
      @Override
      public AdDto addAd (CreateOrUpdateAdDto createOrUpdateAdDto , MultipartFile image) {
            Integer id = adRepository.save(adMapper.toAd(createOrUpdateAdDto)).getId();
            Ad savedAd = adRepository.findById(id).orElseThrow();
            return adMapper.toAdDto(savedAd);
      }
      @Override
      public ExtendedAdDto getAdInformation (Integer id) {
            Ad ad = adRepository.findById(id).orElseThrow();
            return adMapper.toExtendedAdDto(ad);
      }
      @Override
      public final void deleteAd (Integer id) {
            adRepository.deleteById (id);
      }
      @Override
      public AdDto updateAdInformation (Integer id , CreateOrUpdateAdDto createOrUpdateAdDto) {
            Ad ad = adRepository.findById(id).orElseThrow();
            ad.setDescription (createOrUpdateAdDto.getDescription());;
            ad.setTitle(createOrUpdateAdDto.getTitle());
            ad.setPrice(createOrUpdateAdDto.getPrice());
            return adMapper.toAdDto(adRepository.save(ad));
      }
      @Override
      public AdsDto getAuthorizedUserAds() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User currentUser = userRepository.findByEmail(username).orElseThrow();
            return (AdsDto) adMapper.toAdsDto(adRepository.findByAuthor(currentUser));
      }

}
