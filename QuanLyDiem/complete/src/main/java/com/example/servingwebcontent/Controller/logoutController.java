package com.example.servingwebcontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class logoutController {
    @PostMapping("/logout")
    public String logout() {
        return "index"; // chuyển hướng về trang đăng nhập
    }
}
