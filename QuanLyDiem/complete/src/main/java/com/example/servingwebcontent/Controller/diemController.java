package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.diemAiven;
import com.example.servingwebcontent.Model.Diem;
import com.example.servingwebcontent.Model.Monhoc;
import com.example.servingwebcontent.Model.Student;
import com.example.servingwebcontent.database.StudentAiven;
import com.example.servingwebcontent.database.monhocAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class diemController {
    private diemAiven db = new diemAiven();
    private StudentAiven studentDB = new StudentAiven();
    private monhocAiven subjectDB = new monhocAiven();

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
        model.addAttribute("students", studentDB.getAllStudents());
        model.addAttribute("subjects", subjectDB.getAllMonhoc());
        return "diem";
    }

    @PostMapping("/grades/add")
    public String add(@RequestParam("msv") String msv,
                      @RequestParam("maMon") String maMon,
                      @RequestParam("diemMon") double diemMon,
                      Model model) {
        if (msv == null || msv.isEmpty() || maMon == null || maMon.isEmpty()) {
            model.addAttribute("errorMessage", "Vui lòng chọn sinh viên và môn học!");
        } else if (diemMon < 0 || diemMon > 10) {
            model.addAttribute("errorMessage", "Điểm phải từ 0 đến 10!");
        } else {
            try {
                Diem grade = new Diem();
                Student sv = new Student();
                sv.setMsv(msv);
                grade.setStudent(sv);
                Monhoc mh = new Monhoc();
                mh.setMaMon(maMon);
                grade.setMonhoc(mh);
                grade.setDiemMon(diemMon);
                db.addDiem(grade);
                model.addAttribute("successMessage", "Thêm điểm thành công!");
            } catch (Exception e) {
                model.addAttribute("errorMessage", "Lỗi khi thêm điểm: " + e.getMessage());
            }
        }
        List<Diem> grades = db.getAllDiem();
        model.addAttribute("grades", grades);
        model.addAttribute("grade", new Diem());
        model.addAttribute("students", studentDB.getAllStudents());
        model.addAttribute("subjects", subjectDB.getAllMonhoc());
        return "diem";
    }

    @GetMapping("/grades/edit/{msv}/{maMon}")
    public String editForm(@PathVariable String msv, @PathVariable String maMon, Model model) {
        Diem grade = db.findDiem(msv, maMon);
        model.addAttribute("grade", grade);
        model.addAttribute("grades", db.getAllDiem());
        model.addAttribute("students", studentDB.getAllStudents());
        model.addAttribute("subjects", subjectDB.getAllMonhoc());
        return "diem";
    }

    @PostMapping("/grades/edit")
    public String edit(@RequestParam("msv") String msv,
                   @RequestParam("maMon") String maMon,
                   @RequestParam("diemMon") double diemMon,
                   Model model) {
        try {
            if (diemMon < 0 || diemMon > 10) {
                model.addAttribute("errorMessage", "Điểm phải từ 0 đến 10!");
                // Truyền lại dữ liệu để hiển thị form
                List<Diem> grades = db.getAllDiem();
                model.addAttribute("grades", grades);
                model.addAttribute("grade", new Diem());
                model.addAttribute("students", studentDB.getAllStudents());
                model.addAttribute("subjects", subjectDB.getAllMonhoc());
                return "diem";
            }
            Diem grade = new Diem();
            Student sv = new Student();
            sv.setMsv(msv);
            grade.setStudent(sv);
            Monhoc mh = new Monhoc();
            mh.setMaMon(maMon);
            grade.setMonhoc(mh);
            grade.setDiemMon(diemMon);
            db.updateDiem(grade);
            model.addAttribute("successMessage", "Cập nhật điểm thành công!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi cập nhật điểm: " + e.getMessage());
        }
        List<Diem> grades = db.getAllDiem();
        model.addAttribute("grades", grades);
        model.addAttribute("grade", new Diem());
        model.addAttribute("students", studentDB.getAllStudents());
        model.addAttribute("subjects", subjectDB.getAllMonhoc());
        return "diem";
    }

    @GetMapping("/grades/delete/{msv}/{maMon}")
    public String delete(@PathVariable String msv, @PathVariable String maMon, Model model) {
        try {
            db.deleteDiem(msv, maMon);
            model.addAttribute("successMessage", "Xóa điểm thành công!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xóa điểm: " + e.getMessage());
        }
        List<Diem> grades = db.getAllDiem();
        model.addAttribute("grades", grades);
        model.addAttribute("grade", new Diem());
        model.addAttribute("students", studentDB.getAllStudents());
        model.addAttribute("subjects", subjectDB.getAllMonhoc());
        return "diem";
    }

    @GetMapping("/grades/view/{msv}/{maMon}")
    public String view(@PathVariable String msv, @PathVariable String maMon, Model model) {
        Diem grade = db.findDiem(msv, maMon);
        model.addAttribute("viewGrade", grade);
        model.addAttribute("grades", db.getAllDiem());
        model.addAttribute("grade", new Diem());
        model.addAttribute("students", studentDB.getAllStudents());
        model.addAttribute("subjects", subjectDB.getAllMonhoc());
        return "diem";
    }
}
