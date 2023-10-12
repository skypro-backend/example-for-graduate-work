package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exeptions.NotFoundException;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.entities.AdEntity;
import ru.skypro.homework.service.entities.CommentEntity;
import ru.skypro.homework.service.repositories.AdRepository;
import ru.skypro.homework.service.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;

    @Override
    public CommentsDTO receivingAdComments(int adId) {
        AdEntity adEntity = checkForAd(adId);

        List<CommentEntity> commentEntityList = adEntity.getCommentEntityList();

        List<CommentDTO> commentDTOList = commentMapper.toCommentDTOList(commentEntityList);

        return new CommentsDTO(commentEntityList.size(), commentDTOList);
    }

    @Override
    public void deleteComment(int adId, int commentId) {
        CommentEntity commentEntity = checkForAdAndComment(adId, commentId);

        commentRepository.delete(commentEntity);
    }

    @Override
    public CommentDTO addComment(int adId, CreateOrUpdateCommentDTO text) {
        AdEntity adEntity = checkForAd(adId);

        CommentEntity newCommentEntity = commentMapper.createCommentEntity(text, adEntity);

        commentRepository.saveAndFlush(newCommentEntity);

        return commentMapper.toCommentDto(newCommentEntity);
    }

    @Override
    public CommentDTO updateComment(int adId, int commentId, CreateOrUpdateCommentDTO text) {
        CommentEntity commentEntityToUpdate = checkForAdAndComment(adId, commentId);

        commentEntityToUpdate.setText(text.getText());

        commentRepository.saveAndFlush(commentEntityToUpdate);

        return commentMapper.toCommentDto(commentEntityToUpdate);
    }

    private CommentEntity checkForAdAndComment(int adId, int commentId) {
        checkForAd(adId);
        Optional<CommentEntity> commentEntity = commentRepository.findCommentByCommentIdAndAdId(adId, commentId);

        return commentEntity.orElseThrow(() -> new NotFoundException(String.format(
                "Комментарий с индексом \"%s\" не найден для объявления с индексом \"%s\".",
                commentId, adId))
        );
    }

    private AdEntity checkForAd(int adId) {
        return adRepository.findById(adId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Объявление с индексом \"%s\" не найдено.", adId)
                ));
    }
}