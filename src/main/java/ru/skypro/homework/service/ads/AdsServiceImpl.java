package ru.skypro.homework.service.ads;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.projection.CreateOrUpdateAd;
import ru.skypro.homework.projection.ExtendedAd;
import ru.skypro.homework.repository.AdRepository;


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
        return null;
    }

    @Override
    public ExtendedAd getAdFullInfo(Integer id) {
        return adRepository.findAllAdFullInfo(id);
    }

    @Override
    public void deleteAd(Integer id) {
        adRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> updateAd(Integer id, CreateOrUpdateAd properties) {
        return null;
    }

    @Override
    public List<AdDTO> getAllAdsByUser(String user) {
        return adRepository.getAllAdsByUser(user).stream()
                .map(AdMapper::fromAd)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> updateImage(Integer id, MultipartFile image) {
        return null;
    }
}
