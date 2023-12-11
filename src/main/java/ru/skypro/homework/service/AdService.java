package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;


public interface AdService {
    AdsDTO getAllAds();

    AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image);


    ExtendedAdDTO getAdInfo(Long adId);

    Void deleteAd(Long adId);

    AdDTO patchAd(Long adId, CreateOrUpdateAdDTO createOrUpdateAdDTO);

    AdsDTO getAllAdsByAuthor();


    Void patchAdImage(Long adId, MultipartFile image);


    CommentsDTO getComments(Long adId);

    CommentDTO addComment(Long adId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO);

    Void deleteComment(Long adId, Long commentId);

    CommentDTO patchComment(Long adId, Long commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO);
}
