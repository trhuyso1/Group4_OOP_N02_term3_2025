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
        model.addAttribute("student", new Student()); // Luôn truyền student rỗng để form hiện
        return "student";
    }

    @GetMapping("/students/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_form";
    }

    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute Student student) {
        studentDB.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{msv}")
    public String editStudentForm(@PathVariable String msv, Model model) {
        Student student = studentDB.getStudentById(msv);
        model.addAttribute("student", student);
        return "student_form";
    }

    @PostMapping("/students/edit")
    public String editStudent(@ModelAttribute Student student) {
        studentDB.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{msv}")
    public String deleteStudent(@PathVariable String msv) {
        studentDB.deleteStudent(msv);
        return "redirect:/students";
    }

    @GetMapping("/students/view/{msv}")
    public String viewStudent(@PathVariable String msv, Model model) {
        Student student = studentDB.getStudentById(msv);
        model.addAttribute("student", student);
        return "student_view";
    }
}
