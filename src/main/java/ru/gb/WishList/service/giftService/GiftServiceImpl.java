package ru.gb.WishList.service.giftService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.WishList.entities.Gift;
import ru.gb.WishList.exception.GiftWithSuchIdNotFoundException;
import ru.gb.WishList.repository.GiftRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.java.Log;
@Service
@AllArgsConstructor
@Log
@Tag(name="GiftServiceImpl", description="Сервис подарков")
public class GiftServiceImpl implements GiftService{

    @Schema(description = "Репозиторий подарков")
    private final GiftRepository giftRepository;

    @Operation(summary = "Найти все подарки",
            description = "Вывод всех подарков")
    public List<Gift> findAllGifts(){
        log.info("findAllGifts()");
        return giftRepository.findAll();
    }

    @Operation (summary = "Найти подарок по идентификатору",
            description = "Вывод подарка по идентификатору")
    public Gift findGiftById(Long giftId){
        log.info("findGiftById()");
        Optional<Gift> giftOptional = giftRepository.findById(giftId); // Поиск подарка в базе данных
        if (!giftOptional.isPresent()) {
            throw new GiftWithSuchIdNotFoundException("Не найден подарок с таким идентификатором " + giftId);
        }
        return giftOptional.get();
    }
    @Operation (summary = "Добавить подарок в список подарков",
            description = "Сохранение подарка в базе данных")
    public Gift saveGift(Gift gift)
    {
        log.info("saveGift()");
        return giftRepository.save(gift);
    }
}
