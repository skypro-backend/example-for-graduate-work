package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.entity.AdsEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapping.AdsMapper;
import ru.skypro.homework.mapping.CreateAdsMapper;
import ru.skypro.homework.mapping.FullAdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsMapper adsMapper;
    private final CreateAdsMapper createAdsMapper;
    private final FullAdsMapper fullAdsMapper;

    private final AdsRepository adsRepository;
    private final UserRepository userRepository;

    @Override
    public Ads addAds(CreateAds properties, MultipartFile image, Authentication authentication) {

        // mapping from dto to entity
        UserEntity author = userRepository.findUserEntityByUsername(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException(authentication.getName()));
        AdsEntity adsEntity = createAdsMapper.toModel(properties);
        adsEntity.setAuthor(author);
        //TODO картинка
        return adsMapper.toDto(adsRepository.save(adsEntity));
    }

    @Override
    public void deleteAds(Integer id) {
        AdsEntity adsEntity = adsRepository.findById(id).orElseThrow(() -> new AdsNotFoundException(id));
        adsRepository.deleteById(id);
    }

    @Override
    public Ads updateAds(Integer id, CreateAds createAds, Authentication authentication) {
        AdsEntity adsEntity = adsRepository.findById(id).orElseThrow(() -> new AdsNotFoundException(id));
        adsEntity.setDescription(createAds.getDescription());
        adsEntity.setTitle(createAds.getTitle());
        adsEntity.setPrice(createAds.getPrice());
        return adsMapper.toDto(adsRepository.save(adsEntity));
    }

    @Override
    public FullAds getFullAds(Integer id) {
        return fullAdsMapper.toDto(adsRepository.findById(id).orElseThrow(() -> new AdsNotFoundException(id)));
    }

    @Override
    public ResponseWrapperAds getAllAds() {
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setResults(adsMapper.toAdsDtoList(adsRepository.findAll()));
        int countAds = responseWrapperAds.getResults().size();
        responseWrapperAds.setCount(countAds);
        //entity to ads
        return responseWrapperAds;
    }

    @Override
    public ResponseWrapperAds getAdsMe(Authentication authentication) {

        UserEntity author = userRepository.findUserEntityByUsername(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException(authentication.getName()));
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setResults(adsMapper.toAdsDtoList(adsRepository.findAdsEntityByAuthor_Id(author.getId())));
        responseWrapperAds.setCount(responseWrapperAds.getResults().size());
        return responseWrapperAds;
    }
}
