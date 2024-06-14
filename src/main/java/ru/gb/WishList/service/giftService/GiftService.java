package ru.gb.WishList.service.giftService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.gb.WishList.entities.Gift;
import java.util.List;

@Tag(name="GiftService", description="Сервис подарков")
public interface GiftService {
    @Operation (summary = "Найти все подарки",
                description = "Вывод всех подарков")
    public List<Gift> findAllGifts();
    @Operation (summary = "Найти подарок по идентификатору",
            description = "Вывод подарка по идентификатору")
    public Gift findGiftById(Long id);
    @Operation (summary = "Добавить подарок в список подарков",
            description = "Сохранение подарка в базе данных")
    public Gift saveGift(Gift gift);

    @Operation (summary = "Удалить подарок",
            description = "Удалить подарок из базы данных")
    public void deleteGift(Long giftId);
}
