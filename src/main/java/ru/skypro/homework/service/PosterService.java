package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Poster;
import ru.skypro.homework.entity.PosterEntity;
import ru.skypro.homework.exception.PosterNotFoundException;
import ru.skypro.homework.mapping.PosterMapper;
import ru.skypro.homework.repository.PosterRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PosterService {
    private final PosterRepository posterRepository;
    private final PosterMapper posterMapping;
    
    public PosterEntity getById(Integer id) {
        return posterRepository.findById(id).orElseThrow(PosterNotFoundException::new);
    }

    public PosterEntity getByAdsId(Integer adsId) {
        return posterRepository.findByAdsId(adsId).orElseThrow(PosterNotFoundException::new);
    }

    public PosterEntity savePoster(PosterEntity posterEntity) {
        return posterRepository.save(posterEntity);
    }

    public PosterEntity savePoster(Poster poster) {
        return posterRepository.save(fromDTOToEntity(poster));
    }

    public void deletePosterById(Integer id) {
        posterRepository.deleteById(id);
    }

    public Poster fromEntityToDTO(PosterEntity PosterEntity) {
        return posterMapping.toDto(PosterEntity);
    }

    public PosterEntity fromDTOToEntity(Poster poster) {
        return posterMapping.toEntity(poster);
    }

    public List<Poster> fromEntityListToDTOList(List<PosterEntity> posterEntities) {
        return posterMapping.toDtoList(posterEntities);
    }

    public List<PosterEntity> fromDTOListToEntityList(List<Poster> posters) {
        return posterMapping.toEntityList(posters);
    }
}
