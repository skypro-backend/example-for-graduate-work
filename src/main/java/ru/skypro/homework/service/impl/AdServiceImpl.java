package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repositories.AdRepository;
import ru.skypro.homework.repositories.CommentRepository;
import ru.skypro.homework.repositories.ImageRepository;
import ru.skypro.homework.security.GetAuthentication;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;

    @Override
    public AdsDTO getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return mapAdsDto(ads);
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO,
                       MultipartFile image,
                       Authentication authentication) {

        Ad ad = adMapper.toEntity(createOrUpdateAdDTO);
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());

        ad.setAuthor(user);
        ad.setImage(imageService.uploadImage(image));
        adRepository.save(ad);

        return adMapper.toDto(ad);
    }

    @Override
    public ExtendedAdDTO getAd(Long id) {

        return adMapper.toExtendedAdDto(adRepository.findById(id).orElseThrow(()->
                new NotFoundException("Объявление с ID = " + id + " не найдено ")));
    }

    @Override
    @Transactional
    public void deleteAd(Long id, Authentication authentication){

        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID" + id + "не найдено"));

        checkPermit(ad,authentication);
        commentRepository.deleteCommentsByAdId(id);
        imageRepository.deleteById(ad.getImage().getId());
        adRepository.deleteById(id);


    }

    @Override
    public AdDTO updateAd(Long id, CreateOrUpdateAdDTO createOrUpdateAdDTO, Authentication authentication){
        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID" + id + "не найдено"));
        checkPermit(ad, authentication);
        ad.setTitle(createOrUpdateAdDTO.getTitle());
        ad.setDescription(createOrUpdateAdDTO.getDescription());
        ad.setPrice(createOrUpdateAdDTO.getPrice());
        adRepository.save(ad);
        return adMapper.toDto(ad);
    }

    @Override
    public AdsDTO getAdsMe(Authentication authentication) {
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        List<Ad> adList = adRepository.findAdByAuthorId(user.getId());
        return mapAdsDto(adList);
    }

    @Override
    @Transactional
    public void updateAdImage(Long id, MultipartFile image, Authentication authentication){

        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID" + id + "не найдено"));
        checkPermit(ad, authentication);
        Image imageFile = ad.getImage();
        ad.setImage(imageService.uploadImage(image));
        imageService.removeImage(imageFile);
        adRepository.save(ad);

    }

    public void checkPermit(Ad ad, Authentication authentication){
        if (!ad.getAuthor().getEmail().equals(authentication.getName())
                && !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            throw new AccessDeniedException("Вы не можете редактировать или удалять чужое объявление");
        }
    }
    public AdsDTO mapAdsDto(List<Ad> adList) {
        AdsDTO adsDto = new AdsDTO();
        adsDto.setCount(adList.size());
        adsDto.setResults(adList.stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList()));
        return adsDto;
    }
}
