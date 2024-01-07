package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mapping.RegisterDtoToUserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

/**
 * <h2>JavaKidsUserDetailsManager</h2>
 * <br>
 * <b>Methods of parent interface UserDetailsManager {@link UserDetailsManager} </b><br>
 * <p>
 * void createUser(UserDetails user);<br>
 * void updateUser(UserDetails user);<br>
 * void deleteUser(String username);<br>
 * void changePassword(String oldPassword, String newPassword);<br>
 * boolean userExists(String username);
 */
@Component
@RequiredArgsConstructor
public class JavaKidsUserDetailsManager implements UserDetailsManager {

    private final Logger logger = LoggerFactory.getLogger(JavaKidsUserDetailsManager.class);

    private final UserRepository userRepository;

    /**
     * <h2>createUser</h2>
     *
     * @param userDetails data of user to be created. <br>Method does nothing if given user is already existed in database
     *                    <br><b>If user pretty new saves his entity in database</b>
     */
    @Override
    public void createUser(UserDetails userDetails) {

        if (userExists(userDetails.getUsername())) {
            return;
        }
        User newUser = new User(0, "", "", "",
                userDetails.getUsername(), Role.USER, "", userDetails.getPassword());

        userRepository.save(newUser);
    }

    /**
     * <h2>createUser(RegisterDto registerDto) -- NOT OVERRIDING!!</h2>
     * <br> Uses mapper to fill all User entity fields
     * <br> Does nothing if email given with arg is already saved in database
     *
     * @param registerDto new user data from front end
     */
    public void createUser(RegisterDto registerDto) {
        logger.info(registerDto.toString());
        if (userExists(registerDto.getUsername())) {
            return;
        }
        User u = RegisterDtoToUserMapper.INSTANCE.registerDtoToUserMapper(registerDto);
        userRepository.save(u);
    }

    /**
     * Not supported by current version
     *
     * @param user
     */
    @Override
    public void updateUser(UserDetails user) {

    }

    /**
     * Not supported by current version
     *
     * @param username
     */
    @Override
    public void deleteUser(String username) {

    }

    /**
     * Not supported by current version
     *
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    /**
     * <h2>userExists</h2><br>Checks whether user's email is present in database
     *
     * @param username user's email
     * @return true if user exists, or false if not
     */
    @Override
    public boolean userExists(String username) {
        logger.info("User name : " + username);
        return userRepository.findByEmail(username).isPresent();
    }

    /**
     * <h2>loadUserByUsername</h2>
     *
     * @param username user's email
     * @return user details or throw UsernameNotFoundException if user does not exists
     * @throws UsernameNotFoundException throws if user with given emails not present in database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("user not found: " + username);
        }
        return new JavaKidsUserDetails(user.get());
    }
}
