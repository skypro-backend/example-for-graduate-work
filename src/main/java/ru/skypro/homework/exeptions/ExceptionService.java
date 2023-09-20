package ru.skypro.homework.exeptions;

import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    public void methodThrowsException() {
        throw new UnauthorizedException("Вы не прошли авторизацию");
    }
}