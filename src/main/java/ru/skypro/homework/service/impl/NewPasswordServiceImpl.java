package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.mapper.NewPasswordMapper;
import ru.skypro.homework.model.NewPassword;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.NewPasswordRepository;
import ru.skypro.homework.service.NewPasswordService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewPasswordServiceImpl implements NewPasswordService {

    private final NewPasswordMapper newPasswordMapper;
    private final NewPasswordRepository newPasswordRepository;

    private final Logger logger = LoggerFactory.getLogger(NewPasswordServiceImpl.class);


    @Override
    public NewPasswordDto setPassword(Integer id, NewPasswordDto newPasswordDto) {
        logger.info("Method add setPassword invoked!");
        Optional<NewPassword> newPassword = newPasswordRepository.findById(id);
        newPassword.ifPresent(newPasswordDtoFromDB -> {
            newPasswordDtoFromDB.setNewPassword(newPasswordDto.getNewPassword());
            newPasswordRepository.saveAndFlush(newPasswordDtoFromDB);
        });
        return newPasswordMapper.mapToDTO(newPasswordRepository.getById(id));



    }
}
