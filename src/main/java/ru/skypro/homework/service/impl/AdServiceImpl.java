package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.repo.AdRepository;
import ru.skypro.homework.service.AdMapper;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository repository;
    //private final UserRepository userRepository;
    private final AdMapper mapper;

    public AdServiceImpl(AdRepository repository, AdMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
       // this.userRepository = userRepository;
    }


    @Override
    public Ads getAllAds() {
        return mapper.adToDtoList(repository.findAll());
    }

    @Override
    public Ad createAd(CreateOrUpdateAd ad, String image, Integer userId) {
        AdEntity result = new AdEntity();
        result.setDescription(ad.getDescription());
        result.setTitle(ad.getTitle());
        result.setPrice(ad.getPrice());
        result.setImage(image);
       // result.setAuthor(userRepository.findById(userId));
        return mapper.adToDto(repository.save(result));
    }

    @Override
    public ExtendedAd getExtAd(Integer id) {
        AdEntity result = repository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        return mapper.adToExtDto(result);
    }

    @Override
    public Ad deleteAd(Integer id) {
        AdEntity result = repository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        repository.deleteById(id);
        return mapper.adToDto(result);
    }

    @Override
    public Ad pathAd(CreateOrUpdateAd ad, Integer id) {
        AdEntity result = repository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        result.setDescription(ad.getDescription());
        result.setTitle(ad.getTitle());
        result.setPrice(ad.getPrice());
        return mapper.adToDto(repository.save(result));
    }

    @Override
    public Ads getAllAdsForUser(Integer userId) {
        return null; //mapper.adToDtoList(repository.findAdEntitiesByAuthor(userRepository.findById(userId)));
    }

    @Override
    public String pathImageAd(Integer id, String image) {
        AdEntity result = repository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        result.setImage(image);
        repository.save(result);
        return result.getImage();
    }
}
