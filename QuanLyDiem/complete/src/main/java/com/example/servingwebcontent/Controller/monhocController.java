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
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Monhoc> subjects;
        if (keyword != null && !keyword.isEmpty()) {
            subjects = db.searchMonhoc(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            subjects = db.getAllMonhoc();
        }
        model.addAttribute("subjects", subjects);
        model.addAttribute("subject", new Monhoc());
        return "monhoc";
    }

    @PostMapping("/subjects/add")
    public String add(@ModelAttribute Monhoc subject, Model model) {
        try {
            db.addMonhoc(subject);
            model.addAttribute("successMessage", "Thêm môn học thành công!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi thêm môn học: " + e.getMessage());
        }
        List<Monhoc> subjects = db.getAllMonhoc();
        model.addAttribute("subjects", subjects);
        model.addAttribute("subject", new Monhoc());
        return "monhoc";
    }

    @GetMapping("/subjects/edit/{maMon}")
    public String editForm(@PathVariable String maMon, Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        Monhoc subject = db.getMonhocById(maMon);
        List<Monhoc> subjects;
        if (keyword != null && !keyword.isEmpty()) {
            subjects = db.searchMonhoc(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            subjects = db.getAllMonhoc();
        }
        model.addAttribute("subjects", subjects);
        model.addAttribute("subject", subject);
        return "monhoc";
    }

    @PostMapping("/subjects/edit")
    public String edit(@ModelAttribute Monhoc subject, Model model) {
        try {
            db.updateMonhoc(subject);
            model.addAttribute("successMessage", "Cập nhật môn học thành công!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi cập nhật môn học: " + e.getMessage());
        }
        List<Monhoc> subjects = db.getAllMonhoc();
        model.addAttribute("subjects", subjects);
        model.addAttribute("subject", new Monhoc());
        return "monhoc";
    }

    @GetMapping("/subjects/delete/{maMon}")
    public String delete(@PathVariable String maMon, Model model) {
        try {
            db.deleteMonhoc(maMon);
            model.addAttribute("successMessage", "Xóa môn học thành công!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xóa môn học: " + e.getMessage());
        }
        List<Monhoc> subjects = db.getAllMonhoc();
        model.addAttribute("subjects", subjects);
        model.addAttribute("subject", new Monhoc());
        return "monhoc";
    }

    @GetMapping("/subjects/view/{maMon}")
    public String view(@PathVariable String maMon, Model model) {
        Monhoc subject = db.getMonhocById(maMon);
        model.addAttribute("viewSubject", subject);
        List<Monhoc> subjects = db.getAllMonhoc();
        model.addAttribute("subjects", subjects);
        model.addAttribute("subject", new Monhoc());
        return "monhoc";
    }
}
