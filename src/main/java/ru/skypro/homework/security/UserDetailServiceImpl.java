package ru.skypro.homework.security;

import java.time.LocalDateTime;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.repository.UserRepository;

@Service("UserDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public UserDetailServiceImpl(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public boolean login(String email, String password) {
    UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
    if(userEntity == null) return false;
    String encryptedPassword = userEntity.getPassword();
    return passwordEncoder.matches(password,encryptedPassword);
  }

  public boolean register(RegisterReq registerReq) {
    UserEntity userEntity = userRepository.findByEmail(registerReq.getUsername()).orElse(null);
    if(userEntity != null) return false;
    userEntity = new UserEntity();
    userEntity.setFirstName(registerReq.getFirstName());
    userEntity.setLastName(registerReq.getLastName());
    userEntity.setPhone(registerReq.getPhone());
    userEntity.setRegDate(LocalDateTime.now());
    userEntity.setEmail(registerReq.getUsername());
    userEntity.setPassword(passwordEncoder.encode(registerReq.getPassword()));
    userRepository.save(userEntity);
    return true;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByEmail(email)
        .orElseThrow(ElemNotFound::new);
    return SecurityUser.fromUser(userEntity);
  }


}
