package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mapping.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.PictureType;
import ru.skypro.homework.model.User;
import ru.skypro.homework.model.utils.AdFound;
import ru.skypro.homework.model.utils.AdsFound;
import ru.skypro.homework.model.utils.ImageProcessResult;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h2>AdvertisementsService</h2>Service providing tools to manage advertisements
 */
@RequiredArgsConstructor
@Service
public class AdvertisementsService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImagesRepository imagesRepository;
    private final Logger logger = LoggerFactory.getLogger(AdvertisementsService.class);

    /**
     * <h2>getAll()</h2><br>
     * Gets all advertisements from repository
     *
     * @return AdsDto {@link AdsDto} list of entities and number of entities
     */
    public AdsDto getAll() {
        AdsDto adsDto = new AdsDto();

        List<AdDto> adDtoList = adRepository.findAll()
                .stream()
                .map(AdMapper.INSTANCE::adToDto)
                .collect(Collectors.toList());

        adsDto.setCount(adDtoList.size());
        adsDto.setResults(adDtoList);

        return adsDto;
    }

    /**
     * <h2>addNewAd</h2>
     *
     * @param ad DTO ({@link AdDto}) with data to create a new advertisement ({@link ru.skypro.homework.model.Ad} entity)
     * @return DTO of created ad entity
     */
    public AdDto addNewAd(CreateOrUpdateAdDto ad, MultipartFile imageFile, Principal principal) {
        logger.info("Author login name: " + principal.getName() +
                " | CreateOrUpdateAdDto: " + ad.toString() + " | Picture file name: " + imageFile.getName());

        // First, let's create new Ad entity and save to database
        Ad newAd = AdMapper.INSTANCE.CrOUpdToAd(ad);
        newAd.setImage(imageFile.getName());

        Optional<User> authorOptional = userRepository.findByEmail(principal.getName());
        logger.info("authorOptional is present:" + authorOptional.isPresent());
        if (authorOptional.isPresent()) {
            newAd.setAuthor(Math.toIntExact(authorOptional.get().getId()));
        } else {
            newAd.setAuthor(null);
        }
        logger.info("newAd: " + newAd.toString());
        newAd = adRepository.save(newAd);

        // Now let's save advertisement picture in database
        Images itemPicture = Images.builder().
                pictureType(PictureType.ITEM_PICTURE).
                adId(newAd.getPk()).
                userId(newAd.getAuthor()).
                mediaType(imageFile.getContentType()).
                fileSize(imageFile.getSize()).
                build();

        try {
            itemPicture.setData(imageFile.getBytes());
        } catch (IOException e) {
            logger.info(e.toString());
        }

        imagesRepository.save(itemPicture);

        return AdMapper.INSTANCE.adToDto(newAd);
    }


    /**
     * <h2>getAdById</h2>
     *
     * @param id advertisement identifier
     * @return {@link AdFound} instance
     */
    public AdFound getAdById(long id) {

        AdFound adFound = new AdFound();

        adFound.setAd(adRepository.findById(id).orElse(null));
        if (adFound.getAd() == null) {
            adFound.setHttpStatus(HttpStatus.NOT_FOUND);
        }

        adFound.setHttpStatus(HttpStatus.OK);

        return adFound;
    }

    /**
     * <h2>removeAd(long id)</h2>
     *
     * @param id advertisement identifier
     * @return {@link AdFound}
     */
    public AdFound removeAd(long id, String userLoginName) {

        AdFound result = getAdById(id); // Advertisement exists?
        if (result.getHttpStatus().is4xxClientError()) {
            return result;
        }

        if (userLoginName.isEmpty()) { // Logged in?
            result.setHttpStatus(HttpStatus.UNAUTHORIZED);
            return result;
        }

        User actualUser, authorOfAd; // User allowed to delete advertisement?
        actualUser = userRepository.findByEmail(userLoginName).orElse(null);
        if (actualUser == null) {
            result.setHttpStatus(HttpStatus.UNAUTHORIZED);
            return result;
        }

        authorOfAd = userRepository.findById(result.getAd().getAuthor()).orElse(null);
        if (this.isAdmin(userLoginName) |
                actualUser.getEmail().equals(authorOfAd.getEmail())) {
            adRepository.delete(result.getAd());
        }
        return result;
    }

    /**
     * <h2>isAdmin</h2>
     * Checks whether user granted with Admin rights
     *
     * @param userLoginName user login name (email on User entity), nullable
     * @return true if admin role is granted to user specified by provided login
     */
    private boolean isAdmin(String userLoginName) {
        if (userLoginName.isEmpty()) {
            return false;
        }
        Optional<User> userOptional = userRepository.findByEmail(userLoginName);
        return userOptional.filter(user -> Role.ADMIN.equals(user.getUserRole())).isPresent();
    }

    public ResponseEntity<AdsDto> getAdsDtoByUserLoginName(String userLogin) {

        Optional<User> userOptional = userRepository.findByEmail(userLogin);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(new AdsDto(), HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        logger.info("getAdsDtoByUserLoginName | user: " + user.toString());
        AdsFound adsFound = new AdsFound();
        List<Ad> listOfAdvertisements = adRepository.findByAuthor(Math.toIntExact(user.getId()));
        AdsDto adsDto = new AdsDto();

        if (listOfAdvertisements.isEmpty()) {
            return new ResponseEntity<>(adsDto, HttpStatus.OK);
        }

        adsDto.setResults(
                listOfAdvertisements.stream()
                        .map(AdMapper.INSTANCE::adToDto).collect(Collectors.toList()));
        adsDto.setCount(listOfAdvertisements.size());

        adsFound.setResult(adsDto);
        adsFound.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(adsFound.getResult(), adsFound.getHttpStatus());
    }

    /**
     * <h2>updateAd</h2>
     *
     * @param id               advertisement identifier
     * @param updatedAdContent {@link CreateOrUpdateAdDto}
     * @return {@link AdFound} result of update
     */
    public ResponseEntity<AdDto> updateAd(long id, CreateOrUpdateAdDto updatedAdContent, String userLoginName) {

        Ad adById = adRepository.findById(id).orElse(null); // Does specified advertisement exist?
        if (adById == null) {
            return new ResponseEntity<>(new AdDto(), HttpStatus.NOT_FOUND);
        }

        // Consider whether user is allowed to update specified advertisement
        if (userLoginName.isEmpty()) {
            return new ResponseEntity<>(new AdDto(), HttpStatus.UNAUTHORIZED);
        }
        User actualUser = userRepository.findByEmail(userLoginName).orElse(null);
        if (actualUser == null) {
            return new ResponseEntity<>(new AdDto(), HttpStatus.UNAUTHORIZED);
        }
        User author = userRepository.findById(adById.getAuthor()).orElse(null);
        boolean allowed;
        if (author != null) {
            allowed = userLoginName.equals(author.getEmail()) | Role.ADMIN.equals(actualUser.getUserRole());
        } else {
            allowed = Role.ADMIN.equals(actualUser.getUserRole());
        }
        if (allowed) {
            adById.setTitle(updatedAdContent.getTitle());
            adById.setPrice(updatedAdContent.getPrice());
//            adById.setImage(updatedAdContent.getDescription());
            // todo: learn what does CreateOrUpdateAdDto description field mean

            adById = adRepository.save(adById);

            return new ResponseEntity<>(AdMapper.INSTANCE.adToDto(adById), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AdDto(), HttpStatus.UNAUTHORIZED);
    }

    public ImageProcessResult getPhotoByAdId(long id) {
        ImageProcessResult result = new ImageProcessResult();
        result.setImage(null);
        result.setHttpStatus(HttpStatus.NOT_FOUND);
        //todo: fix getPhotoByAdId method in AdvertisementService
        return result;
    }
}

