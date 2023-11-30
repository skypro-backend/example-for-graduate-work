package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.utils.MethodLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


import static java.nio.file.StandardOpenOption.CREATE_NEW;


@Slf4j
@Service
public class AdServiceImpl implements AdService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final AdRepository adRepository;

    public AdServiceImpl(CommentRepository commentRepository, UserRepository userRepository, ImageService imageService, ImageRepository imageRepository, AdRepository adRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.adRepository = adRepository;
    }

    /**
     * Метод для получения всех объявлений в виде DTO моделей
     * @return возвращает все объявления из БД
     */
    @Override
    public AdsDTO getAllAds() {

        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        AdsDTO adsDTO = new AdsDTO();
        List<AdDTO> result = new ArrayList<>();
        adRepository.findAll().forEach(u -> result.add(AdMapper.INSTANCE.adToAdDTO(u)));
        adsDTO.setResults(result);
        adsDTO.setCount(result.size());
        return adsDTO;
    }

    /**
     * Метод для добавления нового объявления в БД
     *
     * @param createOrUpdateAdDTO
     * @param image
     * @return
     */
    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        Ad ad = AdMapper.INSTANCE.createOrUpdateAdDTOToAd(createOrUpdateAdDTO, user);
        ad.setImage(imageService.addImage(image));

        return AdMapper.INSTANCE.adToAdDTO(adRepository.save(ad));
    }

    @Override
    public ExtendedAdDTO getAdInfo(Long adId) {

        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        User user = userRepository.findById(ad.getAuthor().getId()).orElseThrow(UserNotFoundException::new);
        return AdMapper.INSTANCE.toExtendedAdDTO(ad, user);
    }

    @Override
    public Void deleteAd(Long adId) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Long photoId = adRepository.findById(adId).orElseThrow(AdNotFoundException::new).getImage().getId();
        adRepository.deleteById(adId);
        imageRepository.deleteById(photoId);
        commentRepository.deleteAllByAd_Id(adId);
        return null;
    }

    @Override
    public AdDTO patchAd(Long adId, CreateOrUpdateAdDTO createOrUpdateAdDTO) {

        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);

        ad.setTitle(createOrUpdateAdDTO.getTitle());
        ad.setPrice(createOrUpdateAdDTO.getPrice());
        ad.setDescription(createOrUpdateAdDTO.getDescription());
        return AdMapper.INSTANCE.adToAdDTO(adRepository.save(ad));
    }

    @Override
    public AdsDTO getAllAdsByAuthor() {

        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByEmail(name);
        List<AdDTO> result = new ArrayList<>();
        adRepository.findAllByAuthor(user).forEach(u -> result.add(AdMapper.INSTANCE.adToAdDTO(u)));
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setResults(result);
        adsDTO.setCount(result.size());
        return adsDTO;
    }

    @Override
    public String patchAdImage(Long adId, MultipartFile image) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);

//            Path filePath;
//            PhotoAd photoAd = new PhotoAd();
//            try {
//                filePath = Path.of(photoDir, ad.getTitle() + "." + getExtension(Objects.requireNonNull(image.getOriginalFilename())));
//                uploadPhotoAdd(filePath, image);
//                photoAd.setFilePath(filePath.toString());
//                photoAd.setFileSize(image.getSize());
//                photoAd.setMediaType(image.getContentType());
//                photoAd = photoAdRepository.save(photoAd);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        ad.setImage("/"+photoDir+"/"+photoAd.getId());
//        ad.setPhotoAd(photoAd);
//        return adRepository.save(ad).getImage();
        return null;
    }

    @Override
    public CommentsDTO getComments(Long adId) {

        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);

        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentRepository.findAllByAd(ad)) {
            commentDTOList.add(CommentMapper.INSTANCE.toCommentDTO(comment, comment.getAuthor()));
        }
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setResults(commentDTOList);
        commentsDTO.setCount(commentDTOList.size());
        return commentsDTO;
    }

    @Override
    public CommentDTO addComment(Long adId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {

        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);

        Comment comment = new Comment();
        comment.setAd(ad);
        comment.setAuthor(user);
        comment.setText(createOrUpdateCommentDTO.getText());
        comment.setCreatedAt(System.currentTimeMillis());

        return CommentMapper.INSTANCE.toCommentDTO(commentRepository.save(comment), user);

    }

    @Override
    public Void deleteComment(Long adId, Long commentId) {

        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        commentRepository.deleteById(commentId);
        return null;
    }

    @Override
    public CommentDTO patchComment(Long adId, Long commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);

        comment.setText(createOrUpdateCommentDTO.getText());

        return CommentMapper.INSTANCE.toCommentDTO(commentRepository.save(comment), user);
    }


    public boolean isAuthorAd(String username, Long adId) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        return ad.getAuthor().getEmail().equals(username);
    }
    public boolean isAuthorComment(String username, Long commentId) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        return comment.getAuthor().getEmail().equals(username);
    }
}
