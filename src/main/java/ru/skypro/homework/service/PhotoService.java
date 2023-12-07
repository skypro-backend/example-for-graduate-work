package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;

import javax.persistence.criteria.CriteriaBuilder;

public interface PhotoService {
    byte[] getPhoto(Integer pk);
}
