package com.mimi.controller;

import com.mimi.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @GetMapping("/list")
    public R<String> list(){
        return null;
    }
}
