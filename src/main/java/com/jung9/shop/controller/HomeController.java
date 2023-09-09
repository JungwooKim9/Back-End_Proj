package com.jung9.shop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
        // Spring Security에서는 로그인하지 않은 사용자도 Authentication 객체를 생성하여 보관한다.
        // 로그인하지 않은 사용자의 경우, getAuthorities() 메서드를 호출하면 ROLE_ANONYMOUS 권한을 가진다.
        // 따라서, 로그인하지 않은 사용자인지 확인하려면 getAuthorities() 메서드를 호출하여 ROLE_ANONYMOUS 권한을 가졌는지 확인하면 된다.
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {

        }
        return "index";
    }

    @GetMapping("/map")
    public String map() {
        return "map";
    }

    @GetMapping("/test")
    public String Test() {

        return "test";
    }
}
