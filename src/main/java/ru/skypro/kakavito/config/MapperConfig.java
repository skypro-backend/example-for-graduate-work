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


@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addMappings(new PropertyMap<Ad, AdDTO>() {
            @Override
            protected void configure() {
                skip(destination.getPk());
                skip(destination.getImage());
                skip(destination.getAuthor());
            }
        });

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

        modelMapper.addMappings(new PropertyMap<UserDTO, User>() {
        @Override
        protected void configure() {
            skip(destination.getImage());
        }
    });

        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
        @Override
        protected void configure() {
            skip(destination.getImage());
        }
    });
        return modelMapper;
}
}


