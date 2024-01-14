package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository repository;
    private final AdMapper mapper;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Override
    public AdsDto findAll() {
        List<Ad> ads = repository.findAll();
        return new AdsDto(ads.size(), ads.stream().map(mapper::adToDto).collect(Collectors.toList()));
    }

    @Override
    public AdDto addAd (MultipartFile image, CreateOrUpdateAdDto properties, String userName) {

        Ad ad = mapper.adToEntity(properties);
        ImageEntity img = new ImageEntity();
        try {
            img.setBytes(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Image loading error");
        }
        imageRepository.save(img);
        ad.setImage(img);
        UserEntity author = userRepository.findByUserName(userName).orElseThrow();
        ad.setAuthor(author);
        ad.setTittle(properties.getTitle());
        ad.setDescription(properties.getDescription());
        ad.setPrice(properties.getPrice());
        repository.save(ad);

        return mapper.adToDto(ad);
    }

    @Override
    public ExtendedAdDto getAdById(String userName, int id) {

        Ad ad = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Ad with id " + id + " not found"));

        return mapper.extAdToDto(ad);
    }

    @Override
    public void deleteAd(int id, String userName) {

        UserEntity author = userRepository.findByUserName(userName).orElseThrow();
        Ad ad = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Ad with id " + id + " not found"));
        if (author.equals(ad.getAuthor()) || author.getRole().equals(Role.ADMIN)) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException("Author with id " + author.getId() + " haven't ad with id " + ad.getId());
        }
    }

    @Override
    public AdDto updateAd(int id, CreateOrUpdateAdDto dto, String userName) {

        Ad ad = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Ad with id " + id + " not found"));
        UserEntity author = userRepository.findByUserName(userName).orElseThrow();
        if (author.equals(ad.getAuthor()) || author.getRole().equals(Role.ADMIN)) {
            ad.setTittle(dto.getTitle());
            ad.setDescription(dto.getDescription());
            ad.setPrice(dto.getPrice());
            repository.save(ad);
            return mapper.adToDto(ad);
        } else {
            throw new NoSuchElementException("Author with id " + author.getId() + " haven't ad with id " + ad.getId());
        }
    }

    @Override
    public AdsDto getAdsAuthorizedUser(String userName) {

        UserEntity author = userRepository.findByUserName(userName).orElseThrow();
        List<AdDto> result = author.getAds().stream().map(mapper::adToDto).collect(Collectors.toList());

        return new AdsDto(result.size(), result);
    }

    @Override
    public String updateImage(int id, MultipartFile image, String userName) {

        Ad ad = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Ad with id " + id + " not found"));
        ImageEntity img = new ImageEntity();
        try {
            img.setBytes(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Image loading error");
        }
        ad.setImage(img);
        repository.save(ad);

        return image.toString();
    }

}
