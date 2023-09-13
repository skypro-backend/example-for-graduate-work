package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.roles.Role;
import ru.skypro.homework.entity.roles.Roles;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.repository.roles.RolesRepository;
import ru.skypro.homework.repository.users.UsersRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserMapper {

    private final UsersRepository usersRepository;

    private final RolesRepository rolesRepository;

    public CustomUserMapper(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    public User mapToAuthor(Integer author) {
        return usersRepository.findById(author).orElse(null);
    }

    public Set<Roles> mapRoleToSetOfRoles(Role role) {
        if (role == null) {
            return null;
        }
        Set<Roles> roles = new HashSet<>();
        Roles roleEntity = rolesRepository.findByRole(role);
        if (roleEntity != null) {
            roles.add(roleEntity);
        }
        return roles;
    }
}
