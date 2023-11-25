package mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;

@Component
public class AdMapper {
    public Ad mapToAd(Ad ad){
        return new Ad(
                ad.getPk(),
                ad.getAuthor(),
                ad.getImage(),
                ad.getPrice(),
                ad.getTitle(),
                ad.getClass());
    }
}
