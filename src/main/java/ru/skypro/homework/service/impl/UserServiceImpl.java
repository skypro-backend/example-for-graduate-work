package ru.skypro.homework.service.impl;

//@Service
//@Slf4j
//public class UserServiceImpl implements UserService {
//
//  private final UserRepository userRepository;
//  private final UserMapper userMapper;
//
//  @Value("./user_photo")
//  private String userPhotoDit;
//
//  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
//    this.userRepository = userRepository;
//    this.userMapper = userMapper;
//  }
//
//  @Override
//  public UserDTO getUser() {
//    log.info(FormLogInfo.getInfo());
//    long id = 0;//todo исправить id
//    return findById(id);
//  }
//
//  @Override
//  public UserDTO updateUser(UserDTO newUserDto) {
//    log.info(FormLogInfo.getInfo());
//
//    long id = newUserDto.getId();
//
//    UserEntity oldUser = null;
//    try {
//      oldUser = findEntityById(id);
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//    oldUser.setFirstName(newUserDto.getFirstName());
//    oldUser.setLastName(newUserDto.getLastName());
//    oldUser.setEmail(newUserDto.getEmail());
//    oldUser.setPhone(newUserDto.getPhone());
//    oldUser.setRegDate(newUserDto.getRegDate());
//    oldUser.setCity(newUserDto.getCity());
//    oldUser.setImage(newUserDto.getImage());
//    userRepository.save(oldUser);
//
//    return newUserDto;
//  }
//
//  @Override
//  public NewPasswordDTO setPassword(NewPasswordDTO newPassword) {
//    log.info(FormLogInfo.getInfo());
//    return null;
//  }
//
//  @Override
//  public byte[] updateUserImage(MultipartFile image) {
//    log.info(FormLogInfo.getInfo());
//
//    long id = 0;//todo исправить id
//
//    UserEntity userEntity = null;
//    Path filePath = Path.of("");
//
//    try (InputStream is = image.getInputStream();
//        OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
//        BufferedInputStream bis = new BufferedInputStream(is, 1024);
//        BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
//
//      userEntity = findEntityById(id);
//      filePath = Path.of(userPhotoDit, id + "." + getExtension(
//          Objects.requireNonNull(image.getOriginalFilename())));
//      Files.createDirectories(filePath.getParent());
//      Files.deleteIfExists(filePath);
//
//      bis.transferTo(bos);
//
//      String photo = Base64.getEncoder().encodeToString(image.getBytes());
//      userEntity.setPhone(photo);
//      userRepository.save(userEntity);
//      return image.getBytes();
//
//    } catch (Exception e) {
//      try {
//        return image.getBytes();
//      } catch (IOException ex) {
//        throw new RuntimeException(ex);
//      }
////      throw new RuntimeException(e);
//    }
//
//  }
//
//
//  public UserDTO findById(Long id) {
//    log.info(FormLogInfo.getInfo());
//
//    UserEntity user = userRepository.findById(id).orElseThrow(
//        () -> new NotFoundException("user with this id not found"));//todo исправить исключение
//    return userMapper.toDTO(user);
//  }
//
//  @NotNull
//  private UserEntity findEntityById(Long id) throws Exception {
//    log.info(FormLogInfo.getInfo());
//
//    UserEntity user = userRepository.findById(id).orElseThrow(
//        () -> new Exception("user with this id not found"));//todo исправить исключение
//    return user;
//  }
//
//  /**
//   * вспомогательный медот для загрузки фотографий
//   *
//   * @return расширение файла
//   */
//  private String getExtension(String fileName) {
//    log.info(FormLogInfo.getInfo());
//
//    return fileName.substring(fileName.lastIndexOf(".") + 1);
//  }
//
//}
