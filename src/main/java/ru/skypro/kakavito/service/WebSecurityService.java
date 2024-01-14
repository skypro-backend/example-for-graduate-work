package ru.skypro.kakavito.service;

public interface WebSecurityService {
    boolean canAccessInAd(Integer id, String username);
    boolean canAccessInComment(Integer id, String username);

}
