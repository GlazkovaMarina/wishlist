package ru.gb.WishList.service.giftService;

import ru.gb.WishList.domain.Gift;
import java.util.List;

public interface GiftService {
    public List<Gift> findAllGifts();
    public Gift findGiftById(Long id);
    public Gift saveGift(Gift gift);
}
