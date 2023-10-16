package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentsService;

import javax.transaction.Transactional;


@Transactional
@Service
public class CommentsServiceImpl implements CommentsService {

    @Override
    public Comments getFullComments(int id) {
        return null;
    }

    @Override
    public Comments addComments(int id) {
        return null;
    }

    @Override
    public void removeComments(int id, int commentsId) {

    }

    @Override
    public CreateOrUpdateComment updateComments(int id, int commentsId) {
        return null;
    }


}
