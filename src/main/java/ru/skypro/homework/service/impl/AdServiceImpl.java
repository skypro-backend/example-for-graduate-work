package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.account.Role;
import ru.skypro.homework.dto.ads.Ad;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdMapper;
import ru.skypro.homework.service.AdService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserDetails userDetails;
    private static final String USER_NOT_FOUND = "User not found";
    private static final String AD_NOT_FOUND = "Ad not found";
    @Value("${ad.image.dir.path}")
    private String adImageDirPath;

    public AdServiceImpl(CommentRepository commentRepository,
                         UserRepository userRepository,
                         AdRepository adRepository,
                         AdMapper adMapper,
                         UserDetails userDetails) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.userDetails = userDetails;
    }

    @Override
    @Transactional
    public Ad createAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image) throws IOException {
        UserEntity user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        AdEntity adEntity = adRepository.save(adMapper.toAdEntity(createOrUpdateAd, new AdEntity()));
        Path filePath = createPath(image, adEntity);
        adEntity.setUserEntity(user);
        adEntity.setImagePath(filePath.toAbsolutePath().toString());
        return adMapper.toAd(adRepository.save(adEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public Ads getAllAdvertising() {
        List<AdEntity> adEntityList = adRepository.findAll();
        return adMapper.toAds(adEntityList);
    }

    @Override
    @Transactional(readOnly = true)
    public ExtendedAd getAdvertisingById(int id) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(AD_NOT_FOUND));
        return adMapper.toExtendedAd(adEntity);
    }

    @Override
    @Transactional
    public boolean deleteAdvertisingById(int id) throws IOException {
        String userName = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        AdEntity adEntity=adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(AD_NOT_FOUND));
        if (userEntity.getRole() == Role.ADMIN || adEntity.getUserEntity().equals(userEntity)){
            commentRepository.deleteAllByAdEntity_Id(id);
            adRepository.deleteById(id);
            Files.deleteIfExists(Path.of(adEntity.getImagePath()));
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Ad updateAdvertising(int id, CreateOrUpdateAd createOrUpdateAd) {
        String userName = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(AD_NOT_FOUND));
        if (userEntity.getRole() == Role.ADMIN || adEntity.getUserEntity().equals(userEntity)){
            return adMapper.toAd(adRepository.save(adMapper.toAdEntity(createOrUpdateAd, adEntity)));
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Ads getAllAuthenticatedUserAdvertising() {
        List<AdEntity> adEntityList = adRepository.findAllByUserEntityEmail(userDetails.getUsername());
        return adMapper.toAds(adEntityList);
    }

    @Override
    @Transactional
    public boolean updateAdvertisingImage(int id, MultipartFile image) throws IOException {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(AD_NOT_FOUND));
        if (adEntity.getImagePath() != null) {
            Files.deleteIfExists(Path.of(adEntity.getImagePath()));
        }
        adEntity.setImagePath(createPath(image, adEntity).toAbsolutePath().toString());
        adRepository.save(adEntity);
        return true;
    }

    private Path createPath(MultipartFile image, AdEntity adEntity) throws IOException {
        Path filePath = Path.of(adImageDirPath, "Advertisement_" + adEntity.getId() + "."
                + StringUtils.getFilenameExtension(image.getOriginalFilename()));
        AccountServiceImpl.uploadImage(image, filePath);
        return filePath;
    }

    @Override
    public void downloadAdImageFromDB(int adId, HttpServletResponse response) throws IOException {
        AdEntity adEntity = adRepository.findById(adId).orElseThrow(() -> new IllegalArgumentException(AD_NOT_FOUND));
        AccountServiceImpl.downloadImage(response,
                adEntity.getImagePath());
    }

    @Override
    @Transactional(readOnly = true)
    public Ads findByTitle(String title) {
        return adMapper.toAds(adRepository.findAllByTitleLike(title));
    }

}
