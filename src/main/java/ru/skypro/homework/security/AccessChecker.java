package ru.skypro.homework.security;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.reg.Role;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

@Component
public class AccessChecker {

    public void checkAdOwnerOrAdmin(Ad ad, User currentUser) {
        if (currentUser.getRole() == Role.ADMIN) {
            return;
        }
        if (ad.getAuthor() == null || !ad.getAuthor().getId().equals(currentUser.getId())) {
            throw new ForbiddenException();
        }
    }

    public void checkCommentOwnerOrAdmin(Comment comment, User currentUser) {
        if (currentUser.getRole() == Role.ADMIN) {
            return;
        }
        if (comment.getAuthor() == null || !comment.getAuthor().getId().equals(currentUser.getId())) {
            throw new ForbiddenException();
        }
    }
}
