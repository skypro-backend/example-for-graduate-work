package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.exceptions.CommentNotFoundExeption;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.projections.Comments;
import ru.skypro.homework.projections.CreateOrUpdateComment;

import ru.skypro.homework.repository.AdRepo;
import ru.skypro.homework.repository.CommentRepo;
import ru.skypro.homework.service.CommentsService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentRepo commentRepo;
    private final AdServiceImpl adService;
    private final AdRepo adRepo;


    /**
     * Поиск комментария
     */
    @Override
    public Comments getComments(int id) {
        CommentModel commentModel = commentRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return CommentMapper.toComments(commentModel);
    }

    public Comments getAllComments(int adId) {
        adService.getAds(adId);
        List<CommentDTO> commentsDTOList = commentRepo.findAll().stream().map(CommentMapper::toCommentDTO).collect(Collectors.toList());
        return new Comments(commentsDTOList, commentsDTOList.size());
    }


    /**
     * Создание комментария
     */
    @Override
    public Comments addComment(int id, CreateOrUpdateComment createOrUpdateComment) {

//        Comments comments = getComments(id);
//        if (comments != null) {
//            return CommentMapper.toCommentsAdd(createOrUpdateComment);
//        } else {
//            throw new CommentNotFoundExeption();
//        }
        CommentModel commentModel = new CommentModel();
        commentModel.setCreateAt(LocalDateTime.now());
        commentModel.setText(createOrUpdateComment.getText());
        return CommentMapper.toComments(commentModel);
    }

    /**
     * Удаление коментария
     */
    @Override
    public void deleteComment(int id, int commentsId) {
        Optional<AdModel> ad = adRepo.findById(id);
        if (ad.isPresent()) {
            getComments(commentsId);
            commentRepo.deleteById(commentsId);
        } else {
            throw new CommentNotFoundExeption();
        }
    }

    /**
     * Редактирование комментария
     */
    @Override
    public CreateOrUpdateComment updateComment(int id, int commentsId, CreateOrUpdateComment createOrUpdateComment) {
        Optional<AdModel> ad = adRepo.findById(id);
        if (ad.isPresent()) {
            Comments comments = getComments(commentsId);
            return CommentMapper.toCreateOrUpdateComment(comments);
        } else {
            throw new CommentNotFoundExeption();
        }
    }
}
