package ru.skypro.homework.service.ads;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.exception.AdsNotFound;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.projection.CreateOrUpdateAd;
import ru.skypro.homework.projection.ExtendedAd;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class AdsServiceImpl implements AdsService{

    private final AdRepository adRepository;
    @Override
    public List<AdDTO> getAllAds() {
        return adRepository.findAllAds().stream()
                .map(AdMapper::fromAd)
                .collect(Collectors.toList());
    }

    @Override
    public AdDTO createAd(CreateOrUpdateAd properties, MultipartFile image) {
        return AdMapper.fromAd(adRepository.save(
                new Ad(null, properties.getTitle(), null, properties.getPrice(), null))
        );
    }

    @Override
    public ExtendedAd getAdFullInfo(Integer id) {
        return adRepository.findAllAdFullInfo(id).orElseThrow(AdsNotFound::new);
    }

    @Override
    public void deleteAd(Integer id) {
        adRepository.deleteById(id);
    }

    @Override
    public AdDTO updateAd(Integer id, CreateOrUpdateAd properties) {
        Ad adResult = new Ad(id, properties.getTitle(), null, properties.getPrice(), null);
        adResult.setPk(adRepository
                .findById(id)
                .orElseThrow()
                .getPk());
        return AdMapper.fromAd(adRepository.save(adResult));
    }

    @Override
    public List<AdDTO> getAllAdsByUser(String user) {
        return adRepository.getAllAdsByUser(user).stream()
                .map(AdMapper::fromAd)
                .collect(Collectors.toList());
    }

    @Override
    public void updateImage(Integer id, MultipartFile image) {

    }
}
