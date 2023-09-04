package com.jung9.shop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {
    @GetMapping("/secure/cart")
    public String secureCart() {
        return "cart"; // 인증이 필요한 페이지 뷰 이름
    }
}