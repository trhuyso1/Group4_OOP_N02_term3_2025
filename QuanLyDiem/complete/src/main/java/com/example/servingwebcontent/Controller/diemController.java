// package com.example.servingwebcontent.Controller;

// import com.example.servingwebcontent.database.diemAiven;
// import com.example.servingwebcontent.Model.Diem;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @Controller
// public class diemController {
//     private diemAiven db = new diemAiven();

//     @GetMapping("/grades")
//     public String list(Model model) {
//         model.addAttribute("grades", db.getAllDiem());
//         model.addAttribute("grade", new Diem());
//         return "diem";
//     }

//     // Thêm, sửa, xóa, tìm kiếm tương tự StudentController
// }
