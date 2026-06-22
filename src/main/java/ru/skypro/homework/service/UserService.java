package ru.skypro.homework.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.UsersMapper;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.UsersRepository;

@Service
@Transactional
public class UserService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsersRepository usersRepository, UsersMapper usersMapper, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO registerUser(RegisterDTO registerDTO) throws EntityAlreadyExistsException {
        if (usersRepository.existsByUsername(registerDTO.getUsername())) {
            throw new EntityAlreadyExistsException("Username already exists");
        }

        Users user = usersMapper.toEntity(registerDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Users savedUser = usersRepository.save(user);
        return usersMapper.toDTO(savedUser);
    }

    public UserDTO getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return usersMapper.toDTO(user);
    }
}
