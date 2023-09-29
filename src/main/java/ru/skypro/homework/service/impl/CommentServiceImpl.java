package ru.skypro.homework.service.impl;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        Optional<AdEntity> byId = adRepository.findById(adId);

        if (byId.isPresent()) {
            AdEntity adEntity = byId.get();
            List<CommentEntity> commentEntityList = new ArrayList<>(adEntity.getCommentEntityList());
            List<CommentDTO> commentDTOList = commentMapper.toCommentDTOList(commentEntityList);

            return new CommentsDTO(commentDTOList.size(), commentDTOList);
        } else {
            throw new NotFoundException(String.format("Объявление с индексом \"%s\" не найдено.", adId));
        }
    }

    @Override
    public void deleteComment(int adId, int commentId) {
        AdEntity adEntity = adRepository.findById(adId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Объявление с индексом \"%s\" не найдено.", adId)
                ));

        List<CommentEntity> commentEntityList = adEntity.getCommentEntityList();

        Optional<CommentEntity> commentOptional = commentEntityList.stream()
                .filter(comment -> comment.getId() == commentId)
                .findFirst();

        commentOptional.ifPresent(commentRepository::delete);

        if (commentOptional.isEmpty()) {
            throw new NotFoundException(String.format(
                    "Комментарий с индексом \"%s\" не найден для объявления с индексом \"%s\".",
                    commentId, adId
            ));
        }
    }

    @Override
    public CommentDTO addComment(int adId, String text) {
        AdEntity adEntity = adRepository.findById(adId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Объявление с индексом \"%s\" не найдено.", adId)
                ));

        CommentEntity newCommentEntity = new CommentEntity();
        newCommentEntity.setText(text);
        newCommentEntity.setCreatedAt(LocalDateTime.now());
        newCommentEntity.setUser(adEntity.getUser());
        newCommentEntity.setAdEntity(adEntity);

        commentRepository.saveAndFlush(newCommentEntity);

        return commentMapper.toCommentDto(newCommentEntity);
    }
}
