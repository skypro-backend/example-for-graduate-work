package ru.skypro.homework.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateAds;
import ru.skypro.homework.dto.ads.FullAds;
import ru.skypro.homework.dto.ads.ResponseWrapperAds;
import ru.skypro.homework.dto.enums.Role;
import ru.skypro.homework.dto.user.MyUserDetails;
import ru.skypro.homework.exception.*;
import ru.skypro.homework.exception.notFound.AdsNotFoundException;
import ru.skypro.homework.exception.notFound.AvatarNotFoundException;
import ru.skypro.homework.model.AdsModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdsRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsService {
    private final AdsRepository adsRepository;


    public AdsService( AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }

    public ResponseWrapperAds getAllAds() {
        ResponseWrapperAds rwa = new ResponseWrapperAds();
        List<Ads> ads = adsRepository.findAll()
                .stream()
                .map(Ads::fromModel)
                .collect(Collectors.toList());
        rwa.setResults(ads);
        rwa.setCount(ads.size());
        return rwa;
    }

    public Ads addAd( CreateAds createAds, MultipartFile image, Authentication authentication ) {
        UserModel userModel = ((MyUserDetails) authentication.getPrincipal()).getUser();

        if (userModel == null) {
            throw new NotAuthorizedException();
        }

        if (createAds == null) {
            throw new AdsNotFoundException();
        }

        AdsModel newAd = new AdsModel();
        try {
            byte[] bytes = image.getBytes();
            newAd.setImage(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        newAd.setUser(userModel);
        newAd.setDescription(createAds.getDescription());
        newAd.setPrice(createAds.getPrice());
        newAd.setTitle(createAds.getTitle());
        adsRepository.save(newAd);
        return Ads.fromModel(newAd);
    }

    public FullAds getFullAds( Integer id ) {
        AdsModel founded = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        return FullAds.fromModel(founded);
    }

    public Ads updateAds( Integer id, CreateAds createAds, Authentication authentication ) {
        if (!authentication.isAuthenticated()) {
            throw new NotAuthorizedException();
        }
        AdsModel founded = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        UserModel userModel = ((MyUserDetails) authentication.getPrincipal()).getUser();
        if (userModel.getRole().equals(Role.USER)) {
            if (userModel.getPk().equals(founded.getUser().getPk())) {
                founded.setDescription(createAds.getDescription());
                founded.setPrice(createAds.getPrice());
                founded.setTitle(createAds.getTitle());
                adsRepository.save(founded);
                return Ads.fromModel(founded);
            } else {
                throw new AccessDeniedException("Недостаточно прав для изменения чужого объявления!");
            }
        } else {
            founded.setDescription(createAds.getDescription());
            founded.setPrice(createAds.getPrice());
            founded.setTitle(createAds.getTitle());
            adsRepository.save(founded);
            return Ads.fromModel(founded);
        }
    }

    public void deleteAds( Integer id, Authentication authentication ) {
        if (!authentication.isAuthenticated()) {
            throw new NotAuthorizedException();
        }
        AdsModel target = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        UserModel userModel = ((MyUserDetails) authentication.getPrincipal()).getUser();
        if (userModel.getRole().equals(Role.USER)) {
            if (target.getUser().getPk().equals(userModel.getPk())) {
                adsRepository.deleteById(id);
            } else {
                throw new AccessDeniedException("Недостаточно прав для удаления объявления другого пользователя!");
            }
        } else {
            adsRepository.deleteById(id);
        }
    }

    public ResponseWrapperAds getMyAds( Authentication authentication ) {
        if (!authentication.isAuthenticated()) {
            throw new NotAuthorizedException();
        }
        UserModel userModel = ((MyUserDetails) authentication.getPrincipal()).getUser();
        ResponseWrapperAds rwa = new ResponseWrapperAds();

        List<Ads> founded = adsRepository.findAllByUser_Pk(userModel.getPk())
                .stream()
                .map(Ads::fromModel)
                .collect(Collectors.toList());
        rwa.setResults(founded);
        rwa.setCount(founded.size());
        return rwa;
    }

    public MultipartFile updateImage( Integer id, MultipartFile image, Authentication authentication ) {
        AdsModel founded = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        UserModel userModel = ((MyUserDetails) authentication.getPrincipal()).getUser();
        if (userModel.getRole().equals(Role.USER)) {
            if (founded.getUser().getPk().equals(userModel.getPk())) {
                try {
                    byte[] bytes = image.getBytes();
                    founded.setImage(bytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                adsRepository.save(founded);
                return image;
            } else {
                throw new AccessDeniedException("Недостаточно прав для изменения изображения в объявлении другого пользователя!");
            }
        } else {
            try {
                byte[] bytes = image.getBytes();
                founded.setImage(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            adsRepository.save(founded);
            return image;
        }
    }

    public Ads getAdsByTitle( String title ) {
        AdsModel founded = adsRepository.findAdsModelByTitleIsLike(title).orElseThrow(AdsNotFoundException::new);
        return Ads.fromModel(founded);
    }

    public byte[] getAdsAvatarById( Integer id ) {
        AdsModel target = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        if (target.getImage() != null) {
            return target.getImage();
        } else {
            throw new AvatarNotFoundException();
        }
    }
}
