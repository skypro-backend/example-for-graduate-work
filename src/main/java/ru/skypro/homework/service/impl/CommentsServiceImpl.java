package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;

import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

import ru.skypro.homework.service.CommentsService;

import javax.transaction.Transactional;


@Transactional
@Service
public class CommentsServiceImpl implements CommentsService {


    @Override
    public CommentsDTO getComments(int id) {
        return null;
    }

    @Override
    public CommentsDTO addComment(int id) {
        return null;
    }

    @Override
    public void deleteComment(int id, int commentsId) {

    }

    @Override
    public CreateOrUpdateCommentDTO updateComment(int id, int commentsId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        return null;
    }
}
