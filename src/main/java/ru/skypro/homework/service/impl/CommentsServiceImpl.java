package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.exceptions.AdNotFoundException;
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
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private final UserServiceImpl userService;
    private final CommentRepo commentRepo;
    private final AdServiceImpl adService;
    private final AdRepo adRepo;

    /**
     * Получение текущего пользователя
     */
    private UserModel getUser() {
        return userService.find();
    }

    /**
     * Поиск комментария
     */
    @Override
    public Comments getComments(int id) {
        CommentModel commentModel = commentRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return CommentMapper.toComments(commentModel);
    }

    @Override
    public Comments addComment(int id, CreateOrUpdateComment createOrUpdateComment) {
        return null;
    }

    @Override
    public void deleteComment(int id, int commentsId) {
        Optional<AdModel> ad = adRepo.findById(id);
        if (ad.isPresent()) {
            getComments(commentsId);
            commentRepo.deleteById(commentsId);
        } else {
            throw new AdNotFoundException();
        }
    }

    @Override
    public CreateOrUpdateComment updateComment(int id, int commentsId, CreateOrUpdateComment createOrUpdateComment) {
        return null;
    }
}
