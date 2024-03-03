package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.exception.NotEnoughPermissionsException;
import ru.skypro.homework.exception.ResourceNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.security.CustomUserDetails;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.util.SecurityUtil;

import java.util.List;
import java.util.Objects;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final ImageService imageService;
    private final AdMapper adMapper;

    @Autowired
    public AdServiceImpl(AdRepository adRepository, CommentRepository commentRepository, ImageService imageService, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
        this.imageService = imageService;
        this.adMapper = adMapper;
    }

    @Override
    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    @Override
    public Ad getAdById(Long id) {
        return adRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Ad with id %d not found", id)));
    }

    @Override
    public Ad createAd(CreateOrUpdateAd dto, MultipartFile imageFile) {
        Ad ad = adMapper.toAd(dto);
        ad.setImage(imageService.saveImage(imageFile));
        ad.setAuthor(SecurityUtil.getUserDetails().getUser());
        return adRepository.save(ad);
    }

    @Override
    public Ad updateAd(Long id, CreateOrUpdateAd dto) {
        Ad ad = getAdById(id);
        checkPermissions(ad);
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setPrice(dto.getPrice());
        return adRepository.save(ad);
    }

    @Override
    public void deleteAd(Long id) {
        Ad ad = getAdById(id);
        checkPermissions(ad);
        commentRepository.deleteAll(ad.getComments());
        adRepository.deleteById(id);
    }

    @Override
    public List<Ad> getMyAds() {
        CustomUserDetails userDetails = SecurityUtil.getUserDetails();
        User user = userDetails.getUser();
        return user.getAds();
    }

    @Override
    public Ad updateAdImage(Long id, MultipartFile imageFile) {
        Ad ad = getAdById(id);
        checkPermissions(ad);
        ad.setImage(imageService.saveImage(imageFile));
        return adRepository.save(ad);
    }

    private void checkPermissions(Ad ad) {
        CustomUserDetails userDetails = SecurityUtil.getUserDetails();

        if (!Objects.equals(userDetails.getUser(), ad.getAuthor()) && !userDetails.getAuthorities().contains(Role.ADMIN)) {
            throw new NotEnoughPermissionsException("You don't have enough rights");
        }
    }
}
