package ru.gb.WishList.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.ModelAndView;
import ru.gb.WishList.entities.Product;
import ru.gb.WishList.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import ru.gb.WishList.service.userService.UserService;
import ru.gb.WishList.service.productService.ProductService;
import ru.gb.WishList.service.giftService.GiftService;
import lombok.extern.java.Log;
import ru.gb.WishList.files.ExcelBuilder;

import java.util.ArrayList;
import java.util.List;
import ru.gb.WishList.entities.Gift;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="GiftController", description="Контроллер подарков")
@Log
@Controller
@AllArgsConstructor
public class GiftController {

    private UserService userService;
    private ProductService productService;
    private GiftService giftService;

    @Operation(
            summary = "Создание нового подарка",
            description = "Добавление нового подарка в список подарков пользователя"
    )
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/new_present/{user_id}/{item_id}")
    public String getNewPresent(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        log.info("getNewPresent()");
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        model.addAttribute("gift", new Gift());
        return "new_present";
    }
    @Operation(
            summary = "Создание нового подарка",
            description = "Добавление нового подарка в базу данных"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/new_present/{user_id}/{item_id}")
    public String postNewPresent(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId,  Gift gift){
        log.info("postNewPresent()");
        User user = userService.findUserById(userId);
        Product product = productService.findProductById(itemId);
        gift.setOwner(user);
        gift.setProduct(product);
        giftService.saveGift(gift);
        String returnPage = "redirect:/wishlist/" + userId;
        return returnPage;
    }

    @Operation(
            summary = "Карточка подарка",
            description = "Отображение всей информации о подарке"
    )
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/card_present/{user_id}/{gift_id}")
    public String getCardPresent(@PathVariable("user_id") Long userId, @PathVariable("gift_id") Long giftId, Model model){
        log.info("getCardPresent()");
        model.addAttribute("user_id", userId);
        Gift gift = giftService.findGiftById(giftId);
        model.addAttribute("gift", gift);
        return "card_present";
    }

    @Operation(
            summary = "Редактирование карточки подарка",
            description = "Редактирование информации о подарке"
    )
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/edit_present/{user_id}/{gift_id}")
    public String getEditPresent(@PathVariable("user_id") Long userId, @PathVariable("gift_id") Long giftId, Model model){
        log.info("getEditPresent()");
        model.addAttribute("user_id", userId);
        Gift gift = giftService.findGiftById(giftId);
        model.addAttribute("gift", gift);
        model.addAttribute("product_id", gift.getProduct().getId());
        return "edit_present";
    }

    @Operation(
            summary = "Редактирование карточки подарка",
            description = "Обновление информации о подарке в базе данных"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/edit_present/{user_id}/{item_id}/{gift_id}")
    public String postEditPresent(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long productId, @PathVariable("gift_id") Long giftId, Gift gift){
        log.info("postEditPresent()");
        gift.setProduct(productService.findProductById(productId));
        giftService.saveGift(gift);
        String returnPage = "redirect:/card_present/" + userId + "/" + giftId;
        return returnPage;
    }

    @Operation(
            summary = "Список подарков",
            description = "Карточки подарков с краткой информацией"
    )
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/wishlist/{user_id}")
    public String getWishlist(@PathVariable("user_id") Long userId, Model model, HttpServletRequest request){
        log.info("getWishlist()");
        model.addAttribute("user_id", userId);
        List<Gift> gifts =  giftService.findAllGifts();
        if (gifts.isEmpty()){
            model.addAttribute("response", "NoData");
        }
        else{
            model.addAttribute("response", "Data");
        }
        model.addAttribute("gifts", gifts);
        request.getSession().setAttribute("wishlist", gifts);
        return "wishlist";
    }
    @Operation(
            summary = "Скачать список подарков",
            description = "Сохранения на компьютер списка подарков с полной информацией в Excel таблицу"
    )
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/download_wishlist/{user_id}")
    public ModelAndView downloadWishlist(@PathVariable("user_id") Long userId, HttpServletRequest request){
        log.info("downloadWishlist()");
        List<Gift> wishlist = (ArrayList<Gift>)request.getSession().getAttribute("wishlist");
        ModelAndView mav = new ModelAndView(new ExcelBuilder(), "wishlist", wishlist);
        return mav;
    }

    @Operation(
            summary = "Удаление подарка",
            description = "Удаление подарка из базы данных"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/delete_present/{user_id}/{gift_id}")
    public String deleteGift(@PathVariable("user_id") Long userId, @PathVariable("gift_id") Long giftId) {
        log.info("deleteGift()");
        giftService.deleteGift(giftId);
        return "redirect:/wishlist/" + userId;
    }


}
