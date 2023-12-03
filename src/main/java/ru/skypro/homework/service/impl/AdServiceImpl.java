package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final AdRepository adRepository;

    public Ad addAd(MultipartFile image, CreateOrUpdateAd adDetails, UserDetails userDetails){
        UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();

        AdEntity adEntity = adMapper.createOrUpdateAdDTOToAd(adDetails);

        adEntity.setAuthor(userEntity);
        adEntity.setImage(String.valueOf(image));


        adRepository.save(adEntity);

        return adMapper.adToAdDTO(adEntity);
    }

    public Ads getAllAds(){
        List<AdEntity> adEntities = adRepository.findAll();
        List<Ad> listAds = adMapper.ListAdEntityToListAdDTO(adEntities);
        Ads ads = new Ads();
        ads.setCount(listAds.size());
        ads.setResults(listAds);

        return ads;
    }


    public Ads getAdsByCurrentUser(UserDetails userDetails){
        UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<AdEntity> adEntities = userEntity.getAds();
        List<Ad> listAds = adMapper.ListAdEntityToListAdDTO(adEntities);
        Ads ads = new Ads();
        ads.setCount(listAds.size());
        ads.setResults(listAds);

        return ads;
    }

    public ExtendedAd getFullAd(Integer id){
        AdEntity adEntity = adRepository.findById(id).orElseThrow();
        ExtendedAd extendedAd = adMapper.AdEntityToExtendedAdDTO(adEntity);

        return extendedAd;
    }

    public Ad updateAd(Integer id, CreateOrUpdateAd adDetails){
        AdEntity adEntity = adRepository.findById(id).orElseThrow();
        AdEntity adEntityUpdated = adMapper.createOrUpdateAdDTOToAd(adDetails);
        adEntity.setTitle(adEntityUpdated.getTitle());
        adEntity.setPrice(adEntityUpdated.getPrice());
        adEntity.setDescription(adEntityUpdated.getDescription());

        adRepository.save(adEntity);

        return adMapper.adToAdDTO(adEntity);
    }

    public ResponseEntity<String> removeAd(Integer id){
        Optional<AdEntity> adEntityOptional = adRepository.findById(id);
        if(adEntityOptional.isPresent()){
            adRepository.deleteById(id);
            return new ResponseEntity<>("Коментарий удален", HttpStatus.OK);
        } else return new ResponseEntity<>("Коментарий не найден", HttpStatus.NOT_FOUND);
    }

}
