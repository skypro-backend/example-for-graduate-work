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
    public CommentsDTO getFullComments(int id) {
        return null;
    }

    @Override
    public CommentsDTO addComments(int id) {
        return null;
    }

    @Override
    public void removeComments(int id, int commentsId) {

    }

    @Override
    public CreateOrUpdateCommentDTO updateComments(int id, int commentsId) {
        return null;
    }


}
