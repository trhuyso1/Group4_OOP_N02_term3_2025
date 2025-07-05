package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.StudentAiven;
import com.example.servingwebcontent.Model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    private StudentAiven studentDB = new StudentAiven();

    @GetMapping("/students")
    public String listStudents(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            students = studentDB.searchStudents(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            students = studentDB.getAllStudents();
        }
        model.addAttribute("students", students);
        model.addAttribute("student", new Student()); // Để form luôn hiển thị
        return "student";
    }

    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute Student student) {
        studentDB.addStudent(student);
        return "student";
    }

    @GetMapping("/students/edit/{msv}")
    public String editStudentForm(@PathVariable String msv, Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        Student student = studentDB.getStudentById(msv);
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            students = studentDB.searchStudents(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            students = studentDB.getAllStudents();
        }
        model.addAttribute("students", students);
        model.addAttribute("student", student);
        return "student";
    }

    @PostMapping("/students/edit")
    public String editStudent(@ModelAttribute Student student) {
        studentDB.updateStudent(student);
        return "student";
    }

    @GetMapping("/students/delete/{msv}")
    public String deleteStudent(@PathVariable String msv) {
        studentDB.deleteStudent(msv);
        return "student";
    }

    @GetMapping("/students/view/{msv}")
    public String viewStudent(@PathVariable String msv, Model model) {
        Student student = studentDB.getStudentById(msv);
        model.addAttribute("viewStudent", student);
        // Lấy lại danh sách để hiển thị bảng
        List<Student> students = studentDB.getAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("student", new Student());
        return "student";
    }
}
