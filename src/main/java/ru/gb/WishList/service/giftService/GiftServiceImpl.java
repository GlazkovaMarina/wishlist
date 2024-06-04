package ru.gb.WishList.service.giftService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.WishList.domain.Gift;
import ru.gb.WishList.repository.GiftRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GiftServiceImpl implements GiftService{
    private final GiftRepository giftRepository;
    public List<Gift> findAllGifts(){
        return giftRepository.findAll();
    }
    public Gift findGiftById(Long id){
        Optional<Gift> giftOptional = giftRepository.findById(id);
        if (giftOptional.isPresent()) {
            Gift gift = giftOptional.get();
            return gift;
        } else {
            // TODO: переделать возврат, чтоб не было ошибок
            return null;
        }
    }
    public Gift saveGift(Gift gift){
        return giftRepository.save(gift);
    }
}
