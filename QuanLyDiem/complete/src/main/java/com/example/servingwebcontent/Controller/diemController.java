package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.diemAiven;
import com.example.servingwebcontent.Model.Diem;
import com.example.servingwebcontent.Model.Monhoc;
import com.example.servingwebcontent.Model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        return "diem"; // Phải đúng tên file template diem.html
    }

    @PostMapping("/grades/add")
    public String add(@ModelAttribute Diem grade, Model model) {
        boolean success = db.addDiem(grade);
        // Có thể set flash attribute nếu muốn thông báo
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
    public String edit(@ModelAttribute Diem grade, Model model) {
        boolean success = db.updateDiem(grade);
        return "redirect:/grades";
    }

    @GetMapping("/grades/delete/{msv}/{maMon}")
    public String delete(@PathVariable String msv, @PathVariable String maMon, Model model) {
        boolean success = db.deleteDiem(msv, maMon);
        return "redirect:/grades";
    }

    @GetMapping("/grades/view/{msv}/{maMon}")
    public String view(@PathVariable String msv, @PathVariable String maMon, Model model) {
        Diem grade = db.findDiem(msv, maMon);
        model.addAttribute("viewGrade", grade);
        model.addAttribute("grades", db.getAllDiem());
        return "diem";
    }

    public List<Diem> searchByAnyField(String keyword) {
        List<Diem> list = new ArrayList<>();
        String sql = "SELECT d.msv, m.tenMon, d.maMon, d.diemMon " +
                     "FROM diem d " +
                     "JOIN monhoc m ON d.maMon = m.maMon " +
                     "WHERE d.msv LIKE ? OR m.tenMon LIKE ? OR d.maMon LIKE ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String kw = "%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student sv = new Student();
                sv.setMsv(rs.getString("msv"));
                Monhoc mh = new Monhoc();
                mh.setMaMon(rs.getString("maMon"));
                mh.setTenMon(rs.getString("tenMon"));
                Diem diem = new Diem(sv, mh, rs.getDouble("diemMon"));
                list.add(diem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
