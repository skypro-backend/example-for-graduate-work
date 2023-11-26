package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.UpdateUser;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UpdateUserRepository;
import ru.skypro.homework.service.UpdateUserService;

import java.util.Optional;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {
    private final UpdateUserRepository updateUserRepository;

    public UpdateUserServiceImpl(UpdateUserRepository updateUserRepository) {
        this.updateUserRepository = updateUserRepository;
    }
    private final Logger logger = LoggerFactory.getLogger(UpdateUserService.class);

    @Override
    public UpdateUserDto update(Integer id, UpdateUserDto updateUserDto) {
        logger.info("Method add update invoked!");
        Optional<UpdateUser> updateUser = updateUserRepository.findById(id);
        updateUser.ifPresent(updateUserDtoFromDB -> {
            updateUserDtoFromDB.setFirstName(updateUserDto.getFirstName());
            updateUserDtoFromDB.setLastName(updateUserDto.getLastName());
            updateUserDtoFromDB.setPhone(updateUserDto.getPhone());
            updateUserRepository.save(updateUserDtoFromDB);
        });

        return updateUserDto;
    }
}
