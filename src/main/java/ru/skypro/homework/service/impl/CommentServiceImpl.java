package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public CommentDto getComments(int id) {
        return null;
    }

    @Override
    public CommentDto addComment(int id, CommentDto text) {
        return null;
    }

    @Override
    public boolean deleteComment(int adId, int commentId, Authentication authentication) {
        return false;
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CommentDto commentDto, Authentication authentication) {
        return null;
    }
}
