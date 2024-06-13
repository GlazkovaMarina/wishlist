package ru.gb.WishList.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import ru.gb.WishList.entities.ExcelBuilder;

import java.util.ArrayList;
import java.util.List;
import ru.gb.WishList.entities.Gift;
import lombok.AllArgsConstructor;

@Log
@Controller
@AllArgsConstructor
public class GiftController {

    private UserService userService;
    private ProductService productService;
    private GiftService giftService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/new_present/{user_id}/{item_id}")
    public String getNewPresent(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        model.addAttribute("gift", new Gift());
        return "new_present";
    }
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/new_present/{user_id}/{item_id}")
    public String getNewPresent(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId,  Gift gift){
        User user = userService.findUserById(userId);
        Product product = productService.findProductById(itemId);
        gift.setOwner(user);
        gift.setProduct(product);
        giftService.saveGift(gift);
        String returnPage = "redirect:/wishlist/" + userId;
        return returnPage;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/card_present/{user_id}/{gift_id}")
    public String getCardPresent(@PathVariable("user_id") Long userId, @PathVariable("gift_id") Long giftId, Model model){
        model.addAttribute("user_id", userId);
        Gift gift = giftService.findGiftById(giftId);
        model.addAttribute("gift", gift);
        return "card_present";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/edit_present/{user_id}/{gift_id}")
    public String getEditPresent(@PathVariable("user_id") Long userId, @PathVariable("gift_id") Long giftId, Model model){
        model.addAttribute("user_id", userId);
        Gift gift = giftService.findGiftById(giftId);
        model.addAttribute("gift", gift);
        model.addAttribute("product_id", gift.getProduct().getId());
        return "edit_present";
    }
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/edit_present/{user_id}/{product_id}/{gift_id}")
    public String postEditPresent(@PathVariable("user_id") Long userId, @PathVariable("product_id") Long productId, @PathVariable("gift_id") Long giftId, Gift gift){
        gift.setProduct(productService.findProductById(productId));
        giftService.saveGift(gift);
        String returnPage = "redirect:/card_present/" + userId + "/" + giftId;
        return returnPage;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/wishlist/{user_id}")
    public String getWishlist(@PathVariable("user_id") Long userId, Model model, HttpServletRequest request){
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

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/download_wishlist/{user_id}")
    public ModelAndView downloadWishlist(@PathVariable("user_id") Long userId, HttpServletRequest request){
        log.severe("downloadWishlist");
        List<Gift> wishlist = (ArrayList<Gift>)request.getSession().getAttribute("wishlist");
        ModelAndView mav = new ModelAndView(new ExcelBuilder(), "wishlist", wishlist);
        return mav;
    }


}
