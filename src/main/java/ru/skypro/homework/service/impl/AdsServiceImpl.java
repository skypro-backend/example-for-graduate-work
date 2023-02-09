package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.dto.AdsDto;
import ru.skypro.homework.model.dto.CommentDto;
import ru.skypro.homework.model.dto.CreateAdsDto;
import ru.skypro.homework.model.dto.FullAdsDto;
import ru.skypro.homework.model.entity.Ads;
import ru.skypro.homework.model.entity.Comment;
import ru.skypro.homework.model.mapper.AdsMapper;
import ru.skypro.homework.model.mapper.CommentMapper;
import ru.skypro.homework.model.repository.AdsRepository;
import ru.skypro.homework.model.repository.CommentRepository;
import ru.skypro.homework.model.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Service
public class AdsServiceImpl implements ru.skypro.homework.service.AdsService {

    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final AccessService accessService;

    public AdsServiceImpl(CommentRepository commentRepository, AdsRepository adsRepository, UserRepository userProfileRepository, AccessService accessService) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.userRepository = userProfileRepository;
        this.accessService = accessService;
    }

    @Override
    public Collection<AdsDto> getAllAds(String title) {
        Collection<Ads> ads;
        if (!isEmpty(title)) {
            ads = adsRepository.findByTitleContainsOrderByTitle(title);
        } else {
            ads = adsRepository.findAll();
        }

        return AdsMapper.INSTANCE.adsCollectionToAdsDto(ads);

    }

    @Override
    public AdsDto save(CreateAdsDto ads, String email, MultipartFile image) {

        Ads newAds = AdsMapper.INSTANCE.createAdsToAds(ads);
        newAds.setAuthor(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
        log.info("Save ads: " + newAds);
        adsRepository.save(newAds);

        saveImage(image, newAds);      //TODO сделать метод сохранения картинки в таблицу
        log.info("Photo have been saved");

        return AdsMapper
                .INSTANCE
                .adsToAdsDto(newAds);
    }

    @Override
    public Collection<CommentDto> getAdsComments(Integer adsId) {

        Collection<Comment> comments = commentRepository.getByAdsId(adsId);
        return comments.stream()
                .map(CommentMapper.INSTANCE::commentToCommentDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CommentDto addComment(Integer adsId, CommentDto adsComment, @NotNull Authentication authentication) {

        Integer userId = userRepository.getUserProfileId(authentication.getName());

        Comment comment = CommentMapper.INSTANCE.commentDtoToComment(adsComment);

        comment.setAuthor(userId);
        comment.setCreatedAt(LocalDateTime.now().toString());

        commentRepository.save(comment);
        return CommentMapper.INSTANCE.commentToCommentDto(comment);
    }

    @Override
    public CommentDto getAdsComment(Integer adsId, Integer commentId) {

        Comment comment = commentRepository.getByAdsIdAndId(adsId, commentId).orElseThrow(CommentNotFoundException::new);
        return CommentMapper.INSTANCE.commentToCommentDto(comment);
    }

    @Override
    public void deleteComment(Integer adsId, Integer commentId, Authentication authentication) {
        accessService.checkCommentAccess(adsId, commentId, authentication);
        commentRepository.deleteByAdsIdAndId(adsId, commentId);
    }

    @Override
    public CommentDto updateAdsComment(Integer adsId, Integer commentId, @NotNull CommentDto commentDto, Authentication authentication) {
        Comment comment = commentRepository.getByAdsIdAndId(adsId, commentId).orElseThrow(CommentNotFoundException::new);

        accessService.checkCommentAccess(adsId, commentId, authentication);
        // меняется только текст? Дата создания не меняется на дату изменения
        comment.setText(commentDto.getText());
        return CommentMapper.INSTANCE.commentToCommentDto(commentRepository.save(comment));
    }

    @Override
    public void removeAds(Integer adsId, Authentication authentication) {
        accessService.checkAdsAccess(adsId, authentication);
        adsRepository.deleteAllById(adsId);
    }

    @Override
    public FullAdsDto getFullAds(Integer adsId) {
        Ads ads = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        return AdsMapper.INSTANCE.toFullAdsDto(ads);
    }

    @Override
    public AdsDto updateAds(Integer id, CreateAdsDto updatedAds, Authentication authentication) {

        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        accessService.checkAdsAccess(id, authentication);

        ads = AdsMapper.INSTANCE.createAdsToAds(updatedAds);

        return AdsMapper
                .INSTANCE
                .adsToAdsDto(adsRepository.save(ads));
    }

    @Override
    public Collection<AdsDto> getAdsByUser(String email) {
        int authorId = userRepository.getUserProfileId(email);
        Collection<Ads> ads = adsRepository.findByAuthorId(authorId);
        return AdsMapper.INSTANCE.adsCollectionToAdsDto(ads);
    }


    private void saveImage(MultipartFile image, Ads ads) {

    }


}
