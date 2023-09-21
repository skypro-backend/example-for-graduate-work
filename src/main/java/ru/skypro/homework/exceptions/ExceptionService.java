package ru.skypro.homework.exceptions;

import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    public void methodThrowsException() {
        throw new UnauthorizedException("Вы не прошли авторизацию");
    }
}