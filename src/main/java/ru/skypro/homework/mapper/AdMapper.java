package ru.skypro.homework.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.constants.Constants;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.entities.PhotoEntity;
import ru.skypro.homework.entities.UserEntity;
import ru.skypro.homework.repositories.PhotoRepository;
import ru.skypro.homework.repositories.UserRepository;

import java.io.IOException;

@Service
@AllArgsConstructor
public class AdMapper {

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    public Ad mapToAdDto(AdEntity entity) {
        Ad dto = new Ad();
        dto.setAuthor(entity.getAuthor().getId());
        dto.setImage(Constants.URL_PHOTO_CONSTANT + entity.getPhoto().getId());
        dto.setPk(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        return dto;
    }


    public AdEntity mapToAdEntity(CreateOrUpdateAd dto, String username) {
        UserEntity author = userRepository.findUserEntityByUserName(username);
        if (author == null) {
            throw new UserNotFoundException("User not found");
        }
        AdEntity entity = new AdEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setAuthor(author);
        return entity;
    }


    public ExtendedAd mapToExtendedAdDto(AdEntity entity) {
        ExtendedAd dto = new ExtendedAd();
        dto.setPk(entity.getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setAuthorLastName(entity.getAuthor().getLastName());
        dto.setDescription(entity.getDescription());
        dto.setEmail(entity.getAuthor().getUserName());
        dto.setImage(Constants.URL_PHOTO_CONSTANT + entity.getPhoto().getId());
        dto.setPhone(entity.getAuthor().getPhone());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        return dto;
    }


    public PhotoEntity mapMultipartFileToPhoto(MultipartFile image) throws IOException {
        PhotoEntity photo = new PhotoEntity();
        photo.setData(image.getBytes());
        photo.setMediaType(image.getContentType());
        photo.setFileSize(image.getSize());
        return photo;
    }
}