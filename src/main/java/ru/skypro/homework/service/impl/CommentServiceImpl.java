package ru.skypro.homework.service.impl;

import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.repository.AdsRepository;
import ru.skypro.homework.service.repository.CommentRepository;

import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    public final CommentRepository commentRepository;

    public final AdsRepository adsRepository;

    public CommentServiceImpl(CommentRepository commentRepository, AdsRepository adsRepository) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
    }
//
//    public void addComment(Comment comment){
//           Object adID = null;
//           adsRepository.findById((int) adID).orElseThrow()
//
//
//    }
}
