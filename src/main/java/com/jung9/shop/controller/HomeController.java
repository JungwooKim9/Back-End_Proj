package com.jung9.shop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 기본페이지 요청 메소드
    @GetMapping("/")
/*    public String index() {
        return "index";     // templates 폴더의 index.html을 찾아감
    }
*/
    public String securePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            // 사용자는 로그인되었습니다.
            System.out.println("loggedIn: " + authentication.getName());
            model.addAttribute("loggedIn", true);
        } else {
            // 사용자는 로그인되지 않았습니다.
            model.addAttribute("loggedIn", false);
            System.out.println("Not loggedIn: " + authentication.getName());
        }

        return "index";
    }

    @GetMapping("/map")
    public String map() {
        return "map";
    }
}
