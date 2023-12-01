package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.utils.MethodLog;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AdServiceImpl implements AdService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final AdRepository adRepository;

    public AdServiceImpl(CommentRepository commentRepository, UserRepository userRepository, ImageService imageService, AdRepository adRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.adRepository = adRepository;
    }

    /**
     * Метод для получения всех объявлений в виде DTO моделей
     * Метод использует {@link JpaRepository#findAll()}
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
     * Метод использует {@link UserRepository#findByEmail(String)}
     *  {@link ImageService#addImage(MultipartFile)}
     *  {@link JpaRepository#save(Object)}
     *  {@link AdMapper#createOrUpdateAdDTOToAd(CreateOrUpdateAdDTO, User)}
     *  {@link AdMapper#adToAdDTO(Ad)}
     * @param createOrUpdateAdDTO - DTO модель класса {@link CreateOrUpdateAdDTO};
     * @param image - фотография объявления
     * @return возвращает объявление в качестве DTO модели
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

    /**
     * Метод для получения информации об объявлении по id
     * Метод использует {@link AdRepository#findById(Long)}
     *  {@link UserRepository#findById(Long)}
     *  {@link AdMapper#toExtendedAdDTO(Ad, User)}
     * @param adId - id объявления
     * @return возвращает DTO модель объявления
     */
    @Override
    public ExtendedAdDTO getAdInfo(Long adId) {

        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        User user = userRepository.findById(ad.getAuthor().getId()).orElseThrow(UserNotFoundException::new);
        return AdMapper.INSTANCE.toExtendedAdDTO(ad, user);
    }

    /**
     * Метод для удаления объявления по id
     * Метод использует {@link AdRepository#findById(Long)}
     * {@link AdRepository#deleteById(Long)}
     * {@link CommentRepository#deleteAllByAd_Id(Long)}
     * Метод использует {@link ImageService#deleteImage(Long)}
     * @param adId - id объявления
     * @return null
     */

    @Override
    public Void deleteAd(Long adId) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Long imageId = adRepository.findById(adId).orElseThrow(AdNotFoundException::new).getImage().getId();
        adRepository.deleteById(adId);
        imageService.deleteImage(imageId);
        commentRepository.deleteAllByAd_Id(adId);
        return null;
    }

    /**
     * Метод для изменения объявления
     * Метод использует {@link AdRepository#findById(Long)}
     * {@link JpaRepository#save(Object)}
     * {@link AdMapper#adToAdDTO(Ad)}
     * @param adId - id объявления
     * @param createOrUpdateAdDTO - DTO модель класса {@link CreateOrUpdateAdDTO};
     * @return возвращает DTO модель объявления
     */
    @Override
    public AdDTO patchAd(Long adId, CreateOrUpdateAdDTO createOrUpdateAdDTO) {

        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);

        ad.setTitle(createOrUpdateAdDTO.getTitle());
        ad.setPrice(createOrUpdateAdDTO.getPrice());
        ad.setDescription(createOrUpdateAdDTO.getDescription());
        return AdMapper.INSTANCE.adToAdDTO(adRepository.save(ad));
    }

    /**
     * Метод получения всех объявлений данного пользователя
     * Метод использует {@link UserRepository#findByEmail(String)}
     * {@link AdRepository#findAllByAuthor(User)}
     * @return возвращает DTO модель объявления пользователя 
     */
    @Override
    public AdsDTO getAllAdsByAuthor() {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<AdDTO> result = new ArrayList<>();
        adRepository.findAllByAuthor(user).forEach(u -> result.add(AdMapper.INSTANCE.adToAdDTO(u)));
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setResults(result);
        adsDTO.setCount(result.size());
        return adsDTO;
    }

    /**
     * Метод изменения фотографии у объявления
     * Метод использует {@link JpaRepository#findById(Object)}
     * {@link ImageService#addImage(MultipartFile)}
     * {@link ImageService#deleteImage(Long)}
     * {@link JpaRepository#save(Object)}
     * @param adId - id объявления
     * @param image - фотография объявления
     */
    @Override
    @Transactional
    public Void patchAdImage(Long adId, MultipartFile image) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        Long imageId = ad.getImage().getId();
        ad.setImage(imageService.addImage(image));
        imageService.deleteImage(imageId);
        adRepository.save(ad);
        return null;
    }

    /**
     * Метод получения списка всех комментариев объявления
     * Метод использует {@link JpaRepository#findById(Object)}
     * {@link CommentRepository#findAllByAd(Ad)}
     * {@link CommentMapper#toCommentDTO(Comment, User)}
     * @param adId - id объявления
     * @return возвращает DTO модели комментариев
     */
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

    /**
     * Метод добавления комментария к объявлению
     * Метод использует {@link UserRepository#findByEmail(String)}
     * {@link JpaRepository#findById(Object)}
     * {@link JpaRepository#save(Object)}
     * {@link CommentMapper#toCommentDTO(Comment, User)}
     * @param adId - id объявления
     * @param createOrUpdateCommentDTO - DTO модель класса {@link CreateOrUpdateAdDTO};
     * @return возвращает DTO модель комментария
     */
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

    /**
     * Метод удаляет комментарий
     * Метод использует {@link JpaRepository#deleteById(Object)}
     * @param adId - id объявления
     * @param commentId - id объявления
     */
    @Override
    public Void deleteComment(Long adId, Long commentId) {

        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        commentRepository.deleteById(commentId);
        return null;
    }

    /**
     * Метод для изменения комментария
     * Метод использует {@link UserRepository#findByEmail(String)}
     * {@link JpaRepository#findById(Object)}
     * {@link JpaRepository#save(Object)}
     * {@link CommentMapper#toCommentDTO(Comment, User)}
     * @param adId - id объявления
     * @param commentId - id комментария
     * @param createOrUpdateCommentDTO - DTO модель класса {@link CreateOrUpdateAdDTO};
     * @return - возвращает DTO модель комментария
     */
    @Override
    public CommentDTO patchComment(Long adId, Long commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);

        comment.setText(createOrUpdateCommentDTO.getText());

        return CommentMapper.INSTANCE.toCommentDTO(commentRepository.save(comment), user);
    }

    /**
     * Метод проверяет на авторство объявления
     * Метод использует {@link JpaRepository#findById(Object)}
     * @param username - имя пользователя
     * @param adId - id объявления
     */
    public boolean isAuthorAd(String username, Long adId) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        return ad.getAuthor().getEmail().equals(username);
    }

    /**
     * Метод проверяет на авторство комментарий
     * Метод использует {@link JpaRepository#findById(Object)}
     * @param username - имя пользователя
     * @param commentId - id комментария
     */
    public boolean isAuthorComment(String username, Long commentId) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        return comment.getAuthor().getEmail().equals(username);
    }
}
