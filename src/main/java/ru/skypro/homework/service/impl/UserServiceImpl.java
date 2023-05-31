package ru.skypro.homework.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResponseEntity<?> setPassword(NewPassword newPassword) {

        if (!newPassword.getCurrentPassword().equals(new RegisterReq().getPassword())) {
            return ResponseEntity.status(404).build();
        } else {
            return null;
        }


    }

    @Override
    public ResponseEntity<?> getUser() {

        return null;
    }
}
