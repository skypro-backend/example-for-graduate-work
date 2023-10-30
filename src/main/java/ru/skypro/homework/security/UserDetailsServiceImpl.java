package ru.skypro.homework.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;


    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public boolean userExists(String username) {
        Users userNotExists = new Users();
        Users users = usersRepository.findByUsername(username).orElse(userNotExists);
        return !userNotExists.equals(users);
    }

    public void createUser(Register register, String password) {
        Users users = new Users();
        users.setPassword(password);
        users.setPhone(register.getPhone());
        users.setUsername(register.getUsername());
        users.setFirstName(register.getFirstName());
        users.setLastName(register.getLastName());
        users.setRole(register.getRole());
        usersRepository.save(users);
    }
}
