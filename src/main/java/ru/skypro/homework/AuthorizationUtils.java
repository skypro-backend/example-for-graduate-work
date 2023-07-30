package ru.skypro.homework;


import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;


public class AuthorizationUtils {

    public static boolean isUserAdAuthorOrAdmin(Ad ad, User user){
        return user.getUserName().equals(ad.getUser().getUserName()) || user.getRole() == Role.ADMIN;
    }

    public static boolean isUserCommentAuthorOrAdmin(Comment comment, User user){
        return user.getUserName().equals(comment.getUser().getUserName()) || user.getRole() == Role.ADMIN;
    }
}
