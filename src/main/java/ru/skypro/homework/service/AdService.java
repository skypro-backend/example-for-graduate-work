package ru.skypro.homework.service;

import java.util.Map;
import ru.skypro.homework.record.AdRecord;

/**
 * Сервис объявлений
 */
public interface AdService {

  /**
   * Возвращает все объявления
   *
   * @return мапу где ключ по имени столбца в таблице каунт и резалт
   */
  Map<String, Object> getALLAds();

  /**
   * Добавляем новое объявление
   *
   * @return мапу где ключ по имени столбца в таблице каунт и резалт
   */
  AdRecord addAds(AdRecord adRecord);


  /**
   * Возвращает объявления(е) по параметрам
   *
   * @return мапу где ключ по имени столбца в таблице каунт и резалт
   */
  Map<String, Object> getAdsMe(boolean authenticated, String authorities, Object credentials,
      Object details,
      Object principal);

}
