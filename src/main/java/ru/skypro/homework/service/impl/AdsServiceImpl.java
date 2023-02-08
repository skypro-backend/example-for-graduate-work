package ru.skypro.homework.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import ru.skypro.homework.record.AdRecord;
import ru.skypro.homework.service.AdService;

/**
 * Реализация {@link ru.skypro.homework.service.AdService}
 */

@Service
public class AdsServiceImpl implements AdService {

  @Override
  public Map<String, Object> getALLAds() {
    return Map.of("count", 1, "result",
        List.of(new AdRecord(1L, "sda", 1, 2, 3, "sad")));
  }

  @Override
  public AdRecord addAds(AdRecord ad) {
    return new AdRecord(1L, "sda", 1, 2, 3, "sad");
  }

  @Override
  public Map<String, Object> getAdsMe(boolean authenticated, String authorities, Object credentials,
      Object details, Object principal) {
    return Map.of("count", 1, "result",
        List.of(new AdRecord(1L, "sda", 1, 2, 3, "sad")));
  }
}
