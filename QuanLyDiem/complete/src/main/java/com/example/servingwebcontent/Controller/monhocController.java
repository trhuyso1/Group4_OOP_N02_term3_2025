package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.monhocAiven;
import com.example.servingwebcontent.Model.Monhoc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class monhocController {
    private monhocAiven db = new monhocAiven();

    @GetMapping("/subjects")
    public String list(Model model) {
        model.addAttribute("subjects", db.getAllMonhoc());
        model.addAttribute("subject", new Monhoc());
        return "monhoc";
    }

    // Thêm, sửa, xóa, tìm kiếm tương tự StudentController
}
