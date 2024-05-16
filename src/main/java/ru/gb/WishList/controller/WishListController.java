package ru.gb.WishList.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class WishListController {
    @GetMapping
    public String getIndex(){
        return "index";
    }
}