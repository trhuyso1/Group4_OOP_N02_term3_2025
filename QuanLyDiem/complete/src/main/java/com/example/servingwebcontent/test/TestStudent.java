package com.example.servingwebcontent.test;

import com.example.servingwebcontent.CRUD.ListStudent;
import com.example.servingwebcontent.Model.Student;

import java.util.*;

public class TestStudent {
    private static ListStudent studentList = new ListStudent();
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean daThemMau = false;

    private static final Object[][] data = {
        {"SV01", "Nguyen Van A", "Nam", "a@gmail.com", "2000-01-01", "CNTT", "KTPM01"},
        {"SV02", "Tran Thi B", "Nữ", "b@gmail.com", "2001-02-02", "QTKD", "QTKD01"}
    };

    public void dsMauStudent() {
        if (daThemMau) return;
        studentList.setHienThongBao(false);
        for (Object[] row : data) {
            Student s = new Student(
                (String) row[0],
                (String) row[1],
                (String) row[2],
                (String) row[3],
                (String) row[4],
                (String) row[5],
                (String) row[6]
            );
            studentList.them(s);
        }
        daThemMau = true;
    }

    public ListStudent getStudentList() {
        return studentList;
    }

    public void addStudent() {
        System.out.print("Nhập số lượng sinh viên cần thêm: ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("\nNhập sinh viên thứ " + (i + 1));
            System.out.print("Mã SV: ");
            String msv = scanner.nextLine();
            if (studentList.tonTai(msv)) {
                System.out.println("Mã SV đã tồn tại. Bỏ qua.");
                continue;
            }
            System.out.print("Họ tên: ");
            String name = scanner.nextLine();
            System.out.print("Giới tính: ");
            String gender = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Ngày sinh: ");
            String dob = scanner.nextLine();
            System.out.print("Khoa: ");
            String khoa = scanner.nextLine();
            System.out.print("Lớp: ");
            String className = scanner.nextLine();

            Student s = new Student(msv, name, gender, email, dob, khoa, className);
            studentList.them(s);
        }
        System.out.println("\nDanh sách sau khi thêm:");
        inStudent();
    }

    public void inStudent() {
        System.out.println("__Danh sách sinh viên__\n");
        if (studentList.getList().isEmpty()) {
            System.out.println("Danh sách sinh viên rỗng.");
            return;
        }
        inTieuDe();
        for (Student s : studentList.getList()) {
            System.out.println(s);
        }
    }

    private void inTieuDe() {
        System.out.printf("%-8s | %-20s | %-6s | %-20s | %-12s | %-8s | %-10s\n",
            "Mã SV", "Họ tên", "Giới", "Email", "Ngày sinh", "Khoa", "Lớp");
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    public void updateStudent() {
        System.out.print("Nhập mã SV cần sửa: ");
        String msv = scanner.nextLine();
        Student old = studentList.timKiem(msv).stream().findFirst().orElse(null);
        if (old == null) {
            System.out.println("Không tìm thấy sinh viên.");
            return;
        }
        System.out.println("Đang sửa thông tin cho: " + old.getFullName());
        System.out.print("Tên mới: ");
        String name = scanner.nextLine();
        System.out.print("Giới tính mới: ");
        String gender = scanner.nextLine();
        System.out.print("Email mới: ");
        String email = scanner.nextLine();
        System.out.print("Ngày sinh mới: ");
        String dob = scanner.nextLine();
        System.out.print("Khoa mới: ");
        String khoa = scanner.nextLine();
        System.out.print("Lớp mới: ");
        String className = scanner.nextLine();

        Student updated = new Student(msv, name, gender, email, dob, khoa, className);
        studentList.sua(msv, updated);

        System.out.println("\nĐã cập nhật. Danh sách mới:");
        inStudent();
    }

    public void deleteStudent() {
        System.out.print("Nhập mã SV cần xoá: ");
        String msv = scanner.nextLine();
        if (studentList.xoa(msv)) {
            System.out.println("Đã xoá.");
        } else {
            System.out.println("Không tìm thấy sinh viên.");
        }
        System.out.println("\nDanh sách còn lại:");
        inStudent();
    }

    public void timKiemStudent() {
        System.out.print("Nhập từ khoá tìm kiếm (mã, tên, email...): ");
        String keyword = scanner.nextLine();
        List<Student> ketQua = studentList.timKiem(keyword);
        if (ketQua.isEmpty()) {
            System.out.println("Không tìm thấy sinh viên.");
        } else {
            System.out.println("__Kết quả tìm kiếm__:");
            inTieuDe();
            for (Student s : ketQua) {
                System.out.println(s);
            }
        }
    }

    public void thongKeSoLuong() {
        System.out.println("Tổng số sinh viên: " + studentList.getList().size());
    }

    public static boolean checkTonTaiSinhVien(String msv) {
        return studentList.tonTai(msv);
    }
}