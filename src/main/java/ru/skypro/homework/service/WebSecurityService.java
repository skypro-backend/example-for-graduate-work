package ru.skypro.homework.service;

public interface WebSecurityService {
    boolean canAccessInAd(Integer id, String username);
    boolean canAccessInComment(Integer id, String username);
}
