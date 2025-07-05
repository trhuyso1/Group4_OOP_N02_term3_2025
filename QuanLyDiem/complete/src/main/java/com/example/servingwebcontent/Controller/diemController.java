package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.diemAiven;
import com.example.servingwebcontent.Model.Diem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class diemController {
    private diemAiven db = new diemAiven();

    @GetMapping("/grades")
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Diem> grades;
        if (keyword != null && !keyword.isEmpty()) {
            grades = db.searchByAnyField(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            grades = db.getAllDiem();
        }
        model.addAttribute("grades", grades);
        model.addAttribute("grade", new Diem());
        return "diem";
    }

    @PostMapping("/grades/add")
    public String add(@ModelAttribute Diem grade) {
        db.addDiem(grade);
        return "redirect:/grades";
    }

    @GetMapping("/grades/edit/{msv}/{maMon}")
    public String editForm(@PathVariable String msv, @PathVariable String maMon, Model model) {
        Diem grade = db.findDiem(msv, maMon);
        model.addAttribute("grade", grade);
        model.addAttribute("grades", db.getAllDiem());
        return "diem";
    }

    @PostMapping("/grades/edit")
    public String edit(@ModelAttribute Diem grade) {
        db.updateDiem(grade);
        return "redirect:/grades";
    }

    @GetMapping("/grades/delete/{msv}/{maMon}")
    public String delete(@PathVariable String msv, @PathVariable String maMon) {
        db.deleteDiem(msv, maMon);
        return "redirect:/grades";
    }

    @GetMapping("/grades/view/{msv}/{maMon}")
    public String view(@PathVariable String msv, @PathVariable String maMon, Model model) {
        Diem grade = db.findDiem(msv, maMon);
        model.addAttribute("viewGrade", grade);
        model.addAttribute("grades", db.getAllDiem());
        return "diem";
    }
}
