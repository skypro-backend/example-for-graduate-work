package ru.skypro.homework.service.impl;


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
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdServiceImpl implements AdService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;

    public AdServiceImpl(CommentRepository commentRepository, UserRepository userRepository, AdRepository adRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.adRepository = adRepository;
    }

    @Override
    public AdsDTO getAllAds() {
        AdsDTO adsDTO = new AdsDTO();
        List<AdDTO> result = new ArrayList<>();
        adRepository.findAll().forEach(u -> result.add(AdMapper.INSTANCE.adToAdDTO(u)));
        adsDTO.setResults(result);
        adsDTO.setCount(result.size());
        return adsDTO;
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        Ad ad = AdMapper.INSTANCE.createOrUpdateAdDTOToAd(createOrUpdateAdDTO, user);
        ad.setImage(image.getName());
        adRepository.save(ad);
        return AdMapper.INSTANCE.adToAdDTO(ad);
    }

    @Override
    public ExtendedAdDTO getAdInfo(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        User user = userRepository.findById(ad.getAuthor().getId()).orElseThrow(UserNotFoundException::new);
        return AdMapper.INSTANCE.toExtendedAdDTO(ad, user);
    }

    @Override
    public Void deleteAd(Long adId) {
        adRepository.deleteById(adId);
        return null;
    }

    @Override
    public AdDTO patchAd(Long adId, CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);

        ad.setTitle(createOrUpdateAdDTO.getTitle());
        ad.setPrice(createOrUpdateAdDTO.getPrice());
        ad.setDescription(createOrUpdateAdDTO.getDescription());

        return AdMapper.INSTANCE.adToAdDTO(adRepository.save(ad));
    }

    @Override
    public AdsDTO getAllAdsByAuthor() {
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
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        ad.setImage(image.getName());
        return adRepository.save(ad).getImage();
    }

    @Override
    public CommentsDTO getComments(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        User user = ad.getAuthor();

        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentRepository.findAllByAd(ad)) {
            commentDTOList.add(CommentMapper.INSTANCE.toCommentDTO(comment, user));
        }
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setResults(commentDTOList);
        commentsDTO.setCount(commentDTOList.size());
        return commentsDTO;
    }

    @Override
    public CommentDTO addComment(Long adId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        User user = ad.getAuthor();

        Comment comment = new Comment();
        comment.setAd(ad);
        comment.setAuthor(user);
        comment.setText(createOrUpdateCommentDTO.getText());
        comment.setCreatedAt(System.currentTimeMillis());

        return CommentMapper.INSTANCE.toCommentDTO(commentRepository.save(comment), user);

    }

    @Override
    public Void deleteComment(Long adId, Long commentId) {
        commentRepository.deleteById(commentId);
        return null;
    }

    @Override
    public CommentDTO patchComment(Long adId, Long commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        User user = adRepository.findById(adId).orElseThrow(AdNotFoundException::new).getAuthor();
        comment.setText(createOrUpdateCommentDTO.getText());

        return CommentMapper.INSTANCE.toCommentDTO(commentRepository.save(comment), user);
    }

    public boolean isAuthorAd(String username, Long adId) {
        Optional<Ad> adOptional = adRepository.findById(adId);
        return adOptional.map(ad -> ad.getAuthor().getEmail().equals(username)).orElse(false);
    }
    public boolean isAuthorComment(String username, Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        return commentOptional.map(comment -> comment.getAuthor().getEmail().equals(username)).orElse(false);
    }
}
