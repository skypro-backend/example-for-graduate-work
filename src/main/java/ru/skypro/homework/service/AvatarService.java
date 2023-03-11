package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Avatar;
import ru.skypro.homework.entity.AvatarEntity;
import ru.skypro.homework.exception.AvatarNotFoundException;
import ru.skypro.homework.mapping.AvatarMapping;
import ru.skypro.homework.repository.AvatarRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final AvatarMapping avatarMapping;

    public AvatarEntity getAvatarById(Integer id) {
        return avatarRepository.findById(id).orElseThrow(AvatarNotFoundException::new);
    }

    public AvatarEntity getByUserId(Integer userId) {
        return avatarRepository.findByUserId(userId).orElseThrow(AvatarNotFoundException::new);
    }

    public AvatarEntity saveAvatar(AvatarEntity avatarEntity) {
        return avatarRepository.save(avatarEntity);
    }

    public AvatarEntity saveAvatar(Avatar avatar) {
        return avatarRepository.save(fromDTOToEntity(avatar));
    }

    public void deleteAvatarById(Integer id) {
        avatarRepository.deleteById(id);
    }

    public Avatar fromEntityToDTO(AvatarEntity avatarEntity) {
        return avatarMapping.toDto(avatarEntity);
    }

    public AvatarEntity fromDTOToEntity(Avatar avatar) {
        return avatarMapping.toEntity(avatar);
    }

    public List<Avatar> fromEntityListToDTOList(List<AvatarEntity> avatarEntities) {
        return avatarMapping.toDtoList(avatarEntities);
    }

    public List<AvatarEntity> fromDTOListToEntityList(List<Avatar> avatars) {
        return avatarMapping.toEntityList(avatars);
    }
}
