package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exeptions.NotFoundException;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.service.entities.AdEntity;
import ru.skypro.homework.service.entities.CommentEntity;
import ru.skypro.homework.service.repositories.AdRepository;
import ru.skypro.homework.service.repositories.CommentRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentServiceImpl {
    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;


    public CommentsDTO receivingAdComments(int adId) {
        Optional<AdEntity> byId = adRepository.findById(adId);

        if (byId.isPresent()) {
            AdEntity adEntity = byId.get();
            List<CommentEntity> commentEntityList = new ArrayList<>(adEntity.getCommentEntityList());
            List<CommentDTO> commentDTOList = commentEntityList.stream()
                    .map(commentMapper::toCommentDto)
                    .collect(Collectors.toList());

            return new CommentsDTO(commentDTOList.size(), commentDTOList);
        } else {
            throw new NotFoundException(String.format("Объявление с индексом \"%s\" не найдено.", adId));
        }
    }

    public void deleteComment(int adId, int commentId) {
        Optional<AdEntity> adOptional = adRepository.findById(adId);

        AdEntity adEntity = adOptional.orElseThrow(() ->
                new NotFoundException(String.format("Объявление с индексом \"%s\" не найдено.", adId)));

        List<CommentEntity> commentEntityList = adEntity.getCommentEntityList();

        Optional<CommentEntity> commentOptional = commentEntityList.stream()
                .filter(comment -> comment.getId() == commentId)
                .findFirst();

        if (commentOptional.isPresent()) {
            CommentEntity commentToDelete = commentOptional.get();
            commentRepository.delete(commentToDelete);
        } else {
            throw new NotFoundException(String.format("Комментарий с индексом \"%s\" не найден для объявления с индексом \"%s\".", commentId, adId));
        }
    }

}
