package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.repo.AdRepository;
import ru.skypro.homework.repo.UserRepo;
import ru.skypro.homework.service.AdMapper;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.WebSecurityService;
import ru.skypro.homework.util.exceptions.FileNotFoundException;
import ru.skypro.homework.util.exceptions.NotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;


/**
 * Класс {@code AdServiceImpl} предоставляет реализацию интерфейса {@link AdService},
 * предоставляя функциональность для управления рекламными объявлениями в системе.
 *
 * <p>Инъекции зависимостей:
 * <ul>
 *     <li>{@code repository} - репозиторий для взаимодействия с данными рекламных объявлений.</li>
 *     <li>{@code mapper} - маппер для преобразования между сущностями и DTO рекламных объявлений.</li>
 * </ul>
 *
 * <p>Методы сервиса:</p>
 * <ul>
 *     <li>{@code getAllAds} - возвращает все рекламные объявления в виде объекта {@link Ads}.</li>
 *     <li>{@code createAd} - создает новое рекламное объявление на основе данных из объекта
 *     {@link CreateOrUpdateAd}, изображения и идентификатора пользователя. Возвращает созданное объявление.</li>
 *     <li>{@code getExtAd} - возвращает расширенную информацию о рекламном объявлении по его идентификатору.</li>
 *     <li>{@code deleteAd} - удаляет рекламное объявление по его идентификатору. Возвращает удаленное объявление.</li>
 *     <li>{@code pathAd} - обновляет рекламное объявление с использованием данных из объекта {@link CreateOrUpdateAd}
 *     по его идентификатору. Возвращает обновленное объявление.</li>
 *     <li>{@code getAllAdsForUser} - возвращает все рекламные объявления для указанного пользователя
 *     в виде объекта {@link Ads}.</li>
 *     <li>{@code pathImageAd} - обновляет изображение рекламного объявления по его идентификатору.
 *     Возвращает путь к обновленному изображению.</li>
 * </ul>
 *
 * <p>Этот класс является компонентом Spring с аннотацией {@link org.springframework.stereotype.Service},
 * предоставляющим бизнес-логику для управления рекламными объявлениями в системе.</p>
 *
 * @author Michail Z. (GH: HeimTN)
 */
@Service
public class AdServiceImpl implements AdService {
    private final AdRepository repository;
    private final UserRepo userRepository;
    private final AdMapper mapper;
    private final WebSecurityService webSecurityService;

    @Value("${path.to.image.folder}")
    private String uploadDir;

    public AdServiceImpl(AdRepository repository, AdMapper mapper, UserRepo userRepository, WebSecurityServiceImpl webSecurityService){
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.webSecurityService = webSecurityService;
    }


    @Override
    public Ads getAllAds() {
        Collection<AdEntity> result = repository.findAll();
        if(result.isEmpty()){
            throw new NotFoundException("Обьявления не найдены");
        }
        return mapper.adToDtoList(result);
    }

    @Override
    public Ad createAd(CreateOrUpdateAd ad, MultipartFile image) {
        AdEntity result;
        result = mapper.dtoToAd(ad);
        result.setImage(uploadImageHandler(image));
        result.setAuthor(userRepository.findByLogin(getMe()));
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

    @PreAuthorize("@webSecurityServiceImpl.canAccessInAd(#id, principal.username) or hasRole('ADMIN')")
    @Override
    public Ad deleteAd(@Param("id")Integer id) {
        AdEntity result = repository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        repository.deleteById(id);
        return mapper.adToDto(result);
    }

    @PreAuthorize("@webSecurityServiceImpl.canAccessInAd(#id, principal.username) or hasRole('ADMIN')")
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
    public Ads getAllAdsForUser() {
        return mapper.adToDtoList(repository.findAdEntitiesByAuthor(userRepository.findByLogin(getMe())));
    }

    @Override
    public MultipartFile pathImageAd(Integer id, MultipartFile image) {
        AdEntity result = repository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        if(result.getAuthor().getLogin().equals(getMe()) || result.getAuthor().getRole().equals(Role.ADMIN)) {
            result.setImage(uploadImageHandler(image));
            repository.save(result);
            return image;
        }
        throw new AccessDeniedException("Нет доступа");
    }

    @Override
    public byte[] getImageAd(Integer id) {
        AdEntity result = repository.findById(id).orElse(null);
        if(result == null){
        return null;
        }
        try {
            Path path = Paths.get(result.getImage());
            byte[] fileContent = Files.readAllBytes(path);
            return fileContent;
        } catch (IOException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Непредвиденная ошибка, проверьте стактрейс");

    }

    private String getMe(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private String uploadImageHandler(MultipartFile image){
        if(image.isEmpty()){
            throw new FileNotFoundException("Изображение для загрузки не найдено");
        }
        try{
            Path projectDir = Paths.get("").toAbsolutePath();
            Path uploadPath = Paths.get(projectDir.toString(), uploadDir);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(var inputStream = image.getInputStream()){
                Path filePath = uploadPath.resolve(image.getOriginalFilename());
                while(Files.exists(filePath)){
                    filePath = uploadPath.resolve(filePath.getParent()+"/"+1+filePath.getFileName());
                }
                Files.copy(inputStream, filePath);
                return filePath.toString();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        throw new RuntimeException("AdServiceImpl in uploadFileHandler: unexpected error");
    }
}
