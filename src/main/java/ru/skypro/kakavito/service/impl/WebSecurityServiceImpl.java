package ru.skypro.kakavito.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.kakavito.exceptions.AdNotFoundException;
import ru.skypro.kakavito.exceptions.CommentNotFoundException;
import ru.skypro.kakavito.model.Ad;
import ru.skypro.kakavito.model.Comment;
import ru.skypro.kakavito.repository.AdRepo;
import ru.skypro.kakavito.repository.CommentRepo;
import ru.skypro.kakavito.service.WebSecurityService;

@Service
@RequiredArgsConstructor
public class WebSecurityServiceImpl implements WebSecurityService {
    private final AdRepo adRepo;
    private final CommentRepo commentRepo;
    @Override
    public boolean canAccessInAd(Integer id, String username) {
        Ad ad = adRepo.findById(id).orElseThrow(() -> new AdNotFoundException("Ad not found"));
        return ad.getUser().getEmail().equals(username);
    }

    @Override
    public boolean canAccessInComment(Integer id, String username) {
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found"));
        return comment.getUser().getEmail().equals(username);
    }
}
