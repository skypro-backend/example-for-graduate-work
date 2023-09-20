package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.roles.Role;
import ru.skypro.homework.entity.roles.Roles;
import ru.skypro.homework.repository.roles.RolesRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserMapper {

    private final RolesRepository rolesRepository;

    public CustomUserMapper(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
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
