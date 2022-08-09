package ru.skypro.homework.service.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsComment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapStruct.AdsMapper;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;
    private final AdsCommentRepository adsCommentRepository;
    private final UserRepository userRepository;
    private final AdsMapper mapper = Mappers.getMapper(AdsMapper.class);

    public AdsServiceImpl(AdsRepository adsRepository, AdsCommentRepository adsCommentRepository, UserRepository userRepository) {
        this.adsRepository = adsRepository;
        this.adsCommentRepository = adsCommentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseWrapperAds getAllAds() {
        List<Ads> adsList = adsRepository.findAll();
        List<AdsDto> adsDtoList = mapper.adsToAdsDto(adsList);
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        if (!adsDtoList.isEmpty()) {
            responseWrapperAds.setCount(adsDtoList.size());
            responseWrapperAds.setResults(adsDtoList);
        }
        return responseWrapperAds;
    }

    @Override
    public AdsDto addAds(CreateAds createAds) {
        Ads ads = mapper.createAdsToAds(createAds);
        adsRepository.save(ads);
        return mapper.adsToAdsDto(ads);
    }

    @Override
    public ResponseWrapperAds getAdsMe(Boolean authenticated, String authorities0Authority, Object credentials, Object details, Object principal) {
//        пока не понятно что делать со всеми этими параметрами
        List<Ads> adsList = adsRepository.findAll();
        List<AdsDto> adsDtoList = mapper.adsToAdsDto(adsList);
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        if (!adsDtoList.isEmpty()) {
            responseWrapperAds.setCount(adsDtoList.size());
            responseWrapperAds.setResults(adsDtoList);
        }
        return responseWrapperAds;
    }

    @Override
    public ResponseWrapperAdsComment getAdsComments(int pk) {
        List<AdsComment> adsCommentList = adsCommentRepository.findAdsCommentByPk(pk);
        List<AdsCommentDto> adsCommentDtoList = mapper.adsCommentToAdsCommentDto(adsCommentList);
        ResponseWrapperAdsComment responseWrapperAdsComment = new ResponseWrapperAdsComment();
        if (!adsCommentDtoList.isEmpty()) {
            responseWrapperAdsComment.setCount(adsCommentDtoList.size());
            responseWrapperAdsComment.setResults(adsCommentDtoList);
        }
        return responseWrapperAdsComment;
    }

    @Override
    public AdsCommentDto addAdsComment(int pk, AdsCommentDto adsCommentDto) {
        AdsComment adsComment = mapper.adsCommentDtoToAdsComment(adsCommentDto);
        adsCommentRepository.save(adsComment);
        return adsCommentDto;
    }

    @Override
    public AdsCommentDto deleteAdsComment(int pk, int id) {
        AdsComment adsComment = adsCommentRepository.findAdsCommentByPkAndAuthor(pk, id).orElseThrow(NoSuchElementException::new);
        adsCommentRepository.deleteById(adsComment.getId());
        return mapper.adsCommentToAdsCommentDto(adsComment);
    }

    @Override
    public AdsCommentDto getAdsComment(int pk, int id) {
        AdsComment adsComment = adsCommentRepository.findAdsCommentByPkAndAuthor(pk, id).orElseThrow(NoSuchElementException::new);
        return mapper.adsCommentToAdsCommentDto(adsComment);
    }

    @Override
    public AdsCommentDto updateAdsComment(int pk, int id, AdsCommentDto adsCommentDto) {
        AdsComment adsComment = adsCommentRepository.findAdsCommentByPkAndAuthor(pk, id).orElseThrow(NoSuchElementException::new);
        adsComment.setAuthor(userRepository.findById(id).orElseThrow(NoSuchElementException::new));
        adsComment.setPk(adsCommentRepository.findById(pk).orElseThrow(NoSuchElementException::new).getPk());
        adsComment.setText(adsCommentDto.getText());
        adsComment.setCreatedAt(adsCommentDto.getCreatedAt());
        adsCommentRepository.save(adsComment);
        return adsCommentDto;
    }

    @Override
    public AdsDto removeAds(int id) {
        Ads ads = adsRepository.findById(id).orElseThrow(NoSuchElementException::new);
        adsRepository.deleteById(id);
        return mapper.adsToAdsDto(ads);
    }

    @Override
    public FullAds getAds(int id) {
        Ads ads = adsRepository.findById(id).orElseThrow(NoSuchElementException::new);
        User user = userRepository.findById(ads.getAuthor().getId()).orElseThrow(NoSuchElementException::new);
        FullAds fullAds = new FullAds();
        fullAds.setAuthorFirstName(user.getFirstName());
        fullAds.setAuthorLastName(user.getLastName());
        fullAds.setDescription(ads.getDescription());
        fullAds.setImage(ads.getImage());
        fullAds.setEmail(user.getEmail());
        fullAds.setPhone(user.getPhone());
        fullAds.setPk(ads.getPk());
        fullAds.setPrice(ads.getPrice());
        fullAds.setTitle(ads.getTitle());
        return fullAds;
    }

    @Override
    public AdsDto updateAds(int id, AdsDto adsDto) {
        Ads ads = mapper.adsDtoToAds(adsDto);
        ads.setPk(id);
        adsRepository.save(ads);
        return adsDto;
    }
}
