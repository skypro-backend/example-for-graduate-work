package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReq;


public interface AuthService {
    boolean login( String email, String password );

    boolean register( RegisterReq registerReq );
}
