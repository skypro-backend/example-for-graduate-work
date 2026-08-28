package ru.skypro.homework.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для управления объявлениями.
 * Содержит бизнес-логику по созданию, обновлению, удалению и получению объявлений.
 * Основные функции:
 * - получение всех объявлений;
 * - создание нового объявления с сохранением картинки;
 * - получение объявления по ID;
 * - обновление и удаление объявления с проверкой прав доступа;
 * - получение объявлений пользователя;
 * - обновление картинки объявления.
 */
@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;

    /**
     * Получить все объявления.
     * @return список всех объявлений
     */
    public List<Ad> getAllAds() {
        return adRepository.findAll().stream()
                .map(adMapper::toAdDto)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    /**
     * Создать новое объявление.
     * @param createDto DTO с данными объявления
     * @param image     файл картинки объявления
     * @param authorId  ID автора
     * @return Optional с созданным объявлением
     * @throws IOException если ошибка при сохранении картинки
     */
    public Optional<Ad> createAd(CreateOrUpdateAd createDto, MultipartFile image, Integer authorId) throws IOException {
        return userRepository.findById(authorId)
                .flatMap(author -> adMapper.toEntity(createDto)
                        .map(entity -> {
                            entity.setAuthor(author);
                            if (image != null && !image.isEmpty()) {
                                try {
                                    ImageEntity savedImage = imageService.saveImage(image);
                                    entity.setImage(savedImage);
                                } catch (IOException e) {
                                    throw new RuntimeException("Failed to save image", e);
                                }
                            }
                            return adRepository.save(entity);
                        }))
                .flatMap(adMapper::toAdDto);
    }

    /**
     * Получить расширенную информацию об объявлении по ID.
     * @param id ID объявления
     * @return Optional с расширенной информацией
     */
    public Optional<ExtendedAd> getAdById(Integer id) {
        return adRepository.findById(id)
                .flatMap(adMapper::toExtendedAdDto);
    }


    /**
     * Проверить, имеет ли пользователь роль ADMIN.
     * @param authentication данные аутентификации
     * @return true если роль ADMIN, иначе false
     */
    private boolean hasAdminRole(Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

    /**
     * Получить ID пользователя из аутентификации.
     * @param authentication данные аутентификации
     * @return ID пользователя
     * @throws UsernameNotFoundException если пользователь не найден
     */
    private Integer getUserId(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .map(UserEntity::getId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Обновить объявление.
     * @param id             ID объявления
     * @param updateDto      новые данные
     * @param authentication данные аутентификации
     * @return Optional с обновлённым объявлением
     * @throws AccessDeniedException если пользователь не автор и не ADMIN
     */
    public Optional<Ad> updateAd(Integer id, CreateOrUpdateAd updateDto, Authentication authentication) {
        return adRepository.findById(id)
                .map(entity -> {
                    if (!hasAdminRole(authentication) && !entity.getAuthor().getId().equals(getUserId(authentication))) {
                        throw new AccessDeniedException("You are not the author of this ad");
                    }
                    entity.setTitle(updateDto.getTitle());
                    entity.setPrice(updateDto.getPrice());
                    entity.setDescription(updateDto.getDescription());
                    return adRepository.save(entity);
                })
                .flatMap(adMapper::toAdDto);

    }

    /**
     * Удалить объявление.
     * @param id             ID объявления
     * @param authentication данные аутентификации
     * @return true если удаление успешно, иначе false
     * @throws AccessDeniedException если пользователь не автор и не ADMIN
     */
    public boolean deleteAd(Integer id, Authentication authentication) {
        return adRepository.findById(id)
                .map(entity -> {
                    if (!hasAdminRole(authentication) && !entity.getAuthor().getId().equals(getUserId(authentication))) {
                        throw new AccessDeniedException("You are not the author of this ad");
                    }
                    adRepository.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    /**
     * Получить объявления пользователя.
     * @param userId ID пользователя
     * @return список объявлений пользователя
     */
    public List<Ad> getAdsByUser(Integer userId) {
        return adRepository.findByAuthorId(userId).stream()
                .map(adMapper::toAdDto)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    /**
     * Обновить картинку объявления.
     * @param adId           ID объявления
     * @param file           новый файл картинки
     * @param authentication данные аутентификации
     * @return данные обновлённой картинки
     * @throws IOException          если ошибка при сохранении
     * @throws AccessDeniedException если пользователь не автор и не ADMIN
     */
    public byte[] updateAdImage(Integer adId, MultipartFile file, Authentication authentication) throws IOException {
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        if (!hasAdminRole(authentication) && !ad.getAuthor().getId().equals(getUserId(authentication))) {
            throw new AccessDeniedException("You are not the author of this ad");
        }

        ImageEntity image = imageService.saveImage(file);

        if (ad.getImage() != null) {
            imageService.deleteImage(ad.getImage().getId());
        }

        ad.setImage(image);
        adRepository.save(ad);

        return image.getData();
    }
}
