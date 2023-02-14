//package ru.skypro.homework.service.impl;
//
//import ru.skypro.homework.dto.CommentDTO;
//
///**
// * Реализация {@link ru.skypro.homework.service.AdsService}
// */
//@Service
//@Slf4j
//public class AdsServiceImpl implements AdsService {
//
//  private AdsRepository adsRepository;
//  private AdMapper adMapper;
//
//  public AdsServiceImpl(AdsRepository adsRepository, AdMapper adMapper) {
//    this.adsRepository = adsRepository;
//    this.adMapper = adMapper;
//  }
//
//  /**
//   * Получение всех комментариев объявления
//   *
//   * @param pk
//   * @return
//   */
//  @Override
//  public Collection<CommentDTO> getAdsComments(Integer pk) {
//    return  commentMapper.toDTO(CommentRepository.findById(pk));
//  }
//
//  /**
//   * Добавление коментария к объявлению
//   *
//   * @param pk
//   */
//  @Override
//  public void addAdsComments(Integer pk) {
//
//  }
//
//  /**
//   * Удаление комментария конкретного пользователя у объявления
//   *
//   * @param pk
//   * @param id
//   */
//  @Override
//  public void deleteAdsComment(Integer pk, Integer id) {
//
//  }
//
//  @Override
//  public Collection<AdsDTO> getALLAds() {
//    log.info(FormLogInfo.getInfo());
//    return adMapper.toDTOList(adsRepository.findAll());
//  }
//
//  @Override
//  public AdsDTO addAds(PropertiesDTO properties, MultipartFile multipartFile){
//    log.info(FormLogInfo.getInfo());
//
//    AdsDTO adsDTO = new AdsDTO();
//    adsDTO.setTitle(properties.getTitle());
//    adsDTO.setPrice(properties.getPrice());
//    List<String> listOfImage = new ArrayList<>();
//    String content = null;
//    try {
//       content = new String(multipartFile.getBytes(),StandardCharsets.UTF_8);
//    } catch (IOException e) {
//      log.error(FormLogInfo.getCatch());
//    }
//    listOfImage.add(content);
//    adsDTO.setImage(listOfImage);
//    // Раскомментить когда будут таблицы
//    //adsRepository.save(adMapper.toEntity(adsDTO));
//    return adsDTO;
//  }
//
//
//  public CommentDTO getComments(String adPk, int id) {
//     CommentDTO commentDTO = adsRepository.findByAuthorAndId(adPk,id);
//      return commentMapper.toDTO(commentDTO);
//  }
//
//  @Override
//  public void deleteComments(String adPk, int id) {
//  }
//
//  @Override
//  public CommentDTO updateComments(String adPk, int id, CommentDTO commentDTO) {
//    return null;
//  }
//
//  @Override
//  public void removeAds(int id) {
//  }
//
//  @Override
//  public AdsDTO getAds(int id) {
//    return adMapper.toDTO(AdsRepository.findById(id));
//  }
//
//  @Override
//  public AdsDTO updateAds(int id) {
//    return null;
//  }
//
//}
