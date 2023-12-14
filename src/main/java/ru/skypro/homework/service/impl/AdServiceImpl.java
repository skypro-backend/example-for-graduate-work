package ru.skypro.homework.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.ImageMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private Logger LoggerFactory;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ImageService imageService;
    private final ImageMapper imageMapper;
    private final AdMapper adMapper;
    private final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(AdServiceImpl.class));

    /**
     * Getting all ads
     * using {@link AdMapper#mapToListOfDTO(List)}, {@link AdRepository#findAll()}
     * @return Ads
     */
    @Override
    public Ads findAllAds() {
        return adMapper.mapToListOfDTO(adRepository.findAll());
    }

    /**
     * Adding an ad
     * <br>
     * Creating ad using {@link AdMapper#createFromCreateOrUpdateAd(CreateOrUpdateAd, User)},
     * {@link ImageService#uploadImage(MultipartFile)},
     * {@link UserRepository#findByUsername(String)}
     * <br>
     * Saving ad using {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param createOrUpdateAd
     * @param authentication
     * @param multipartFile
     * @return Ad
     */
    @Override
    public AdDTO createAd(CreateOrUpdateAd createOrUpdateAd,
                          Authentication authentication,
                          MultipartFile multipartFile) throws IOException {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        logger.info("Обьявление - " + createOrUpdateAd);
        Image imageToDB = imageService.uploadImage(multipartFile);
        Ad ad = adMapper.createFromCreateOrUpdateAd(createOrUpdateAd, user);
        ad.setImage(imageToDB);
        adRepository.save(ad);
        logger.info("Создание обьявления");
        return adMapper.mapToDTO(ad);
    }

    /**
     * Getting ad by its id using
     * {@link AdRepository#findByPk(Integer)}
     * @param id
     * @return
     */
    @Override
    public ExtendedAd getAd(int id) {
        Ad ad = adRepository.findByPk(id);
        return adMapper.mapToExtended(ad);
    }

    /**
     * Deleting ad by its id using
     * {@link CommentRepository#deleteAllByAdPk(Integer)}, {@link AdRepository#deleteByPk(int)}
     * @param id
     * @return
     */
    @Override
    public void deleteAd(int id) {
        commentRepository.deleteAllByAdPk(id);
        adRepository.deleteByPk(id);
    }

    /**
     * Updating ad
     * <br>
     * Saving ad in DB by repository method {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * <br>
     * Using setters and getters from dto CreateOrUpdateAd {@link CreateOrUpdateAd}
     *
     * @param id
     * @param createOrUpdateAd
     * @return Ad
     */
    @Override
    public AdDTO updateAd(int id,
                          CreateOrUpdateAd createOrUpdateAd) {
        Ad ad = adRepository.findByPk(id);
        ad.setTitle(createOrUpdateAd.getTitle());
        ad.setPrice(createOrUpdateAd.getPrice());
        ad.setDescription(createOrUpdateAd.getDescription());
        adRepository.save(ad);
        return adMapper.mapToDTO(ad);
    }

    /**
     * Getting all user ads using {@link Authentication#getName()}, {@link UserRepository#findByUsername(String)}
     * @param authentication
     * @return
     */
    @Override
    public Ads findAllUserAds(Authentication authentication) {
        String username = authentication.getName();
        Integer authorId = userRepository.findByUsername(username).getId();
        return adMapper.mapToListOfDTO(adRepository.findAllByAuthorId(authorId));
    }

    /**
     * Updating user image using {@link AdRepository#findByPk(Integer)}, {@link ImageService#uploadImage(MultipartFile)}, 
     * {@link AdRepository#save(Object)}, {@link ImageMapper#mapToDTO(Image)}
     * @param id
     * @param multipartFile
     * @return String
     * @throws IOException
     */
    @Override
    public String editAdImage(int id,
                                MultipartFile multipartFile) throws IOException{
        Ad ad = adRepository.findByPk(id);
        Image imageToDB = imageService.uploadImage(multipartFile);
        ad.setImage(imageToDB);
        adRepository.save(ad);
        return imageMapper.mapToDTO(imageToDB).getUrl();
    }

}
