package ru.skypro.homework.service.ads;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.AdRepository;

@Data
@Service
public class AdsServiceImpl implements AdsService{

    private final AdRepository adRepository;

}
