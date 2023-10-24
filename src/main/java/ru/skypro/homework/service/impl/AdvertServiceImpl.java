package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.mapper.AdvertMapper;
import ru.skypro.homework.model.Advert;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdvertRepository;
import ru.skypro.homework.service.AdvertService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class AdvertServiceImpl implements AdvertService {
    private final UserService userService;
    private final ImageService imageService;
    private final AdvertRepository repository;
    private final AdvertMapper mapper;

    /**
     * Получение текущего пользователя
     */
    private User getUser() {
        return userService.find();
    }

    /**
     * Нахождение объявления
     */
    @Override
    public Advert find(int id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Создание объявления
     */
    @Override
    public AdDto createAdvert(CreateOrUpdateAdDto dto, MultipartFile image) {
        var advert = mapper.toAdvert(dto);
        var user = getUser();
        String filename;
        try {
            filename = imageService.create(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        advert.setImage("/images/" + filename);
        advert.setAuthor(user);
        repository.save(advert);
        return mapper.advertToAdvertDto(advert);
    }

    /**
     * Чтение объявления по id
     */
    @Override
    public ExtendedAdDto getAdvertById(int id) {
        var advert = find(id);
        return mapper.toExtendedDto(advert);
    }

    /**
     * Чтение всех объявлений автора
     */
    @Override
    public AdsDto getAdvert() {
        var authorId = getUser().getId();
        var advertList = repository.findByAuthorId(authorId);
        var advertDtoList = mapper.advertsToAdvertDtos(advertList);
        return new AdsDto(advertDtoList);
    }

    /**
     * Чтение всех объявлений всех авторов
     */
    @Override
    public AdsDto getAllAdverts() {
        var advertList = repository.findAll();
        var advertDtoList = mapper.advertsToAdvertDtos(advertList);
        return new AdsDto(advertDtoList);
    }

    /**
     * Редактирование обьявления
     */
    @Override
    public AdDto updateAdvert(int id, CreateOrUpdateAdDto dto) {
        var advert = find(id);
        mapper.updateAdFromDto(dto, advert);
        repository.save(advert);
        return mapper.advertToAdvertDto(advert);
    }

    /**
     * Обновление обьявления
     */
    @Override
    public String update(int id, MultipartFile image) {
        var advert = find(id);
        String filename;
        try {
            filename = imageService.create(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        advert.setImage("/images/" + filename);
        repository.save(advert);
        return filename;
    }

    /**
     * Удаление объявления
     */
    @Override
    public void deleteAdvert(int id) {
        var advert = find(id);
        repository.delete(advert);
    }
}
