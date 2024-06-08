package ru.gb.WishList.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.java.Log;

@Log
@Controller
@AllArgsConstructor
public class StartController {

    @GetMapping("/index")
    public String getStartPage(){
        log.severe("Get index");
        return "index";
    }
}
