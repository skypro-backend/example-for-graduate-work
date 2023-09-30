package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.transformer.AdTransformer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository usersRepository;
    private final AdTransformer adTransformer;

    public AdServiceImpl(AdRepository adRepository, UserRepository usersRepository, AdTransformer adTransformer) {
        this.adRepository = adRepository;
        this.usersRepository = usersRepository;
        this.adTransformer = adTransformer;
    }

    @Override
    @Transactional
    public Ads getAllAds() {
        Ads allAds = new Ads();
        List<AdEntity> list = (List<AdEntity>) adRepository.findAll();
        allAds.setCount(list.size());
        allAds.setResults(list.stream().map(adTransformer::adEntityToAd).collect(Collectors.toList()));
        return allAds;
    }

    @Override
    @Transactional
    public Ad postAd(CreateOrUpdateAd properties, MultipartFile file, String userName) {
        UserEntity author = usersRepository.findByUsername(userName);
        AdEntity adEntity = adTransformer.createOrUpdateAdToAdEntity(properties, author);
        AdEntity createdAdEntity = adRepository.save(adEntity);
        return adTransformer.adEntityToAd(createdAdEntity);
    }

    @Override
    @Transactional
    public ExtendedAd getAdById(int id) throws AdNotFoundException {
        return adRepository.findById(id).map(adTransformer::adEntityToExtendedAd)
                .orElseThrow(() -> new AdNotFoundException("Not found ad with id = " + id));
    }

    @Override
    @Transactional
    public void deleteAdById(int id) {
        adRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Ad patchAdById(int id, CreateOrUpdateAd createOrUpdateAd) throws AdNotFoundException {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new AdNotFoundException("Not found ad with id = " + id));
        adEntity.setTitle(createOrUpdateAd.getTitle());
        adEntity.setPrice(createOrUpdateAd.getPrice());
        adEntity.setDescription(createOrUpdateAd.getDescription());
        AdEntity updatedAdEntity = adRepository.save(adEntity);
        return adTransformer.adEntityToAd(updatedAdEntity);
    }

    @Override
    @Transactional
    public Ads getMyAds(String userName) {
        UserEntity author = usersRepository.findByUsername(userName);
        List<AdEntity> list = adRepository.findAllByAuthor(author);
        Ads myAds = new Ads();
        myAds.setCount(list.size());
        myAds.setResults(list.stream().map(adTransformer::adEntityToAd).collect(Collectors.toList()));
        return myAds;
    }

    @Override
    @Transactional
    public void patchAdsImageById(int id, MultipartFile file) {
        //todo: уточнить про возвращаемый октет-стрим и доделать!!!
    }
}
