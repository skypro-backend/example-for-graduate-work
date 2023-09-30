package ru.skypro.homework.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exeptions.NotFoundException;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.entities.AdEntity;
import ru.skypro.homework.service.entities.CommentEntity;
import ru.skypro.homework.service.repositories.AdRepository;
import ru.skypro.homework.service.repositories.CommentRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;

    @Override
    public CommentsDTO receivingAdComments(int adId) {
        AdEntity adEntity = checkForAd(adId);

        List<CommentEntity> commentEntityList = new ArrayList<>(adEntity.getCommentEntityList());

        List<CommentDTO> commentDTOList = commentMapper.toCommentDTOList(commentEntityList);

        return new CommentsDTO(commentDTOList.size(), commentDTOList);
    }

    @Override
    public void deleteComment(int adId, int commentId) {
        CommentEntity commentEntity = checkForAdAndComment(adId, commentId);

        commentRepository.delete(commentEntity);
    }

    @Override
    public CommentDTO addComment(int adId, String text) {
        AdEntity adEntity = checkForAd(adId);

        String commentText = parseCommentText(text);

        CommentEntity newCommentEntity = commentMapper.createCommentEntity(commentText, adEntity);

        commentRepository.saveAndFlush(newCommentEntity);

        return commentMapper.toCommentDto(newCommentEntity);
    }

    @Override
    public CommentDTO updateComment(int adId, int commentId, String text) {
        checkForAdAndComment(adId, commentId);

        CommentEntity commentEntityToUpdate = checkForAdAndComment(adId, commentId);

        commentEntityToUpdate.setText(parseCommentText(text));

        commentRepository.saveAndFlush(commentEntityToUpdate);

        return commentMapper.toCommentDto(commentEntityToUpdate);
    }

    private CommentEntity checkForAdAndComment(int adId, int commentId) {
        AdEntity adEntity = checkForAd(adId);

        List<CommentEntity> commentEntityList = adEntity.getCommentEntityList();

        return commentEntityList.stream()
                .filter(comment -> comment.getId() == commentId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format(
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


    private String parseCommentText(String text) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(text);
            return jsonNode.get("text").asText();
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка при парсинге", e);
        }
    }
}
