package ru.skypro.kakavito.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skypro.kakavito.dto.AdDTO;
import ru.skypro.kakavito.dto.CommentDTO;
import ru.skypro.kakavito.dto.ExtendedAdDTO;
import ru.skypro.kakavito.dto.UserDTO;
import ru.skypro.kakavito.model.Ad;
import ru.skypro.kakavito.model.Comment;
import ru.skypro.kakavito.model.User;

/**
 * Класс для создания и конфигурации ModelMapper
 */
@Configuration
public class MapperConfig {

    /**
     * Создание ModelMapper через @Bean
     *
     * @return modelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        /**
         * Настройка преобразования сущности Ad в AdDTO
         *
         * @param pk
         * @param image
         * @param author
         * @return AdDTO
         * @see Ad
         * @see AdDTO
         */
        modelMapper.addMappings(new PropertyMap<Ad, AdDTO>() {
            @Override
            protected void configure() {
                skip(destination.getPk());
                skip(destination.getImage());
                skip(destination.getAuthor());
            }
        });

        /**
         * Настройка преобразования сущности Ad в ExtendedAdDTO
         *
         * @param pk
         * @param image
         * @param phone
         * @param email
         * @param authorFirstName
         * @param authorLastName
         * @return ExtendedAdDTO
         * @see Ad
         * @see ExtendedAdDTO
         */
        modelMapper.addMappings(new PropertyMap<Ad, ExtendedAdDTO>() {
            @Override
            protected void configure() {
                skip(destination.getPk());
                skip(destination.getImage());
                skip(destination.getPhone());
                skip(destination.getEmail());
                skip(destination.getAuthorFirstName());
                skip(destination.getAuthorLastName());
            }
        });

        /**
         * Настройка преобразования сущности Comment в CommentDTO
         *
         *@param createdAt
         *@param authorImage
         *@param author
         *@param authorFirstName
         *@param pk
         *@return CommentDTO
         *@see Comment
         *@see CommentDTO
         */
        modelMapper.addMappings(new PropertyMap<Comment, CommentDTO>() {
            @Override
            protected void configure() {
                skip(destination.getCreatedAt());
                skip(destination.getAuthorImage());
                skip(destination.getAuthor());
                skip(destination.getAuthorFirstName());
                skip(destination.getPk());
            }
        });

        /**
         * Настройка преобразования ДТО UserDTO в User
         *
         *@param image
         *@return User
         *@see UserDTO
         *@see User
         */
        modelMapper.addMappings(new PropertyMap<UserDTO, User>() {
            @Override
            protected void configure() {
                skip(destination.getImage());
            }
        });

        /**
         * Настройка преобразования сущности User в UserDTO
         *
         *@param image
         *@return UserDTO
         *@see User
         *@see UserDTO
         */
        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                skip(destination.getImage());
            }
        });
        return modelMapper;
    }
}


