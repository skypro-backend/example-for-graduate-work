package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.UserAlreadyAddedException;
import ru.skypro.homework.models.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User create(User user) {
        if(userRepository.findByFirstNameAndLastName(
                user.getFirstName(),
                user.getLastName()).isPresent()){
            throw new UserAlreadyAddedException(
                    "User with that initial is already added!");
        }
        return userRepository.save(user);
    }

    @Override
    public User read(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NoSuchElementException("This user not found!");
        }
        return user.get();
    }

    @Override
    public User update(User user) {
        Optional<User> check = userRepository.findById(user.getId());
        if(check.isEmpty()){
            throw new NoSuchElementException("This user not found!");
        }

        return userRepository.save(user);
    }

    @Override
    public User delete(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NoSuchElementException("This user not found!");
        }
        userRepository.deleteById(id);

        User deleteUser = user.get();
        return deleteUser;
    }
}
