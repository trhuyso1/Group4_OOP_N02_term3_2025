package com.example.servingwebcontent.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.userAiven;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Value("${server.port}")
    private String serverPort;
    
    private userAiven userService = new userAiven();

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("port", serverPort);
        return "index"; // File index.html trong templates
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        if (username.equals("admin") && password.equals("111111")) {
            session.setAttribute("username", "admin");
            session.setAttribute("role", "admin");
            return "giaodienchinh";
        }
        if (userService.authenticateUser(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("role", "user");
            return "giaodienchinh";
        }
        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
        model.addAttribute("port", serverPort);
        return "index";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("port", serverPort);
        return "register"; // File register.html trong templates
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("confirmPassword") String confirmPassword,
                           Model model) {

        System.out.println("📝 Đăng ký tài khoản: " + username);

        // Kiểm tra mật khẩu xác nhận
        if (!password.equals(confirmPassword)) {
            System.out.println("Mật khẩu xác nhận không khớp: " + username);
            model.addAttribute("error", "Mật khẩu xác nhận không khớp");
            model.addAttribute("port", serverPort);
            return "register";
        }

        // Kiểm tra độ dài mật khẩu
        if (password.length() < 6) {
            System.out.println("Mật khẩu quá ngắn: " + username);
            model.addAttribute("error", "Mật khẩu phải có ít nhất 6 ký tự");
            model.addAttribute("port", serverPort);
            return "register";
        }

        // Kiểm tra tên đăng nhập không được là admin
        if (username.equalsIgnoreCase("admin")) {
            System.out.println("Username không được là admin: " + username);
            model.addAttribute("error", "Tên đăng nhập 'admin' đã được sử dụng");
            model.addAttribute("port", serverPort);
            return "register";
        }
        if (userService.registerUser(username, password)) {
            System.out.println("Đăng ký thành công: " + username);
            model.addAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            model.addAttribute("port", serverPort);
            return "index";
        } else {
            System.out.println("Username đã tồn tại: " + username);
            model.addAttribute("error", "Tên đăng nhập đã tồn tại");
            model.addAttribute("port", serverPort);
            return "register";
        }
    }

    @GetMapping("/giaodienchinh")
    public String mainPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (username == null) {
            // Nếu chưa đăng nhập, chuyển về trang login
            return "login";
        }
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "giaodienchinh";
    }
}
