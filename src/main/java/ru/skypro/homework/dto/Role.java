package ru.skypro.homework.dto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;
@Getter
@RequiredArgsConstructor
public enum Role {

    USER(Set.of(Accesses.BASIC)),
    ADMIN(Set.of(Accesses.BASIC, Accesses.DELETE_ANY_AD, Accesses.DELETE_ANY_COMMENT,
                                         Accesses.UPDATE_ANY_AD, Accesses.UPDATE_ANY_COMMENT));
    private final Set<Accesses> accesses;
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getAccesses().stream()
                .map(accesses -> new SimpleGrantedAuthority(accesses.name()))
                .collect(Collectors.toSet());
    }
}
