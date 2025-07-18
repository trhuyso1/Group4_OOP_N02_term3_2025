package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentAiven {
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://mysql-1af5a7c5-quanlydiem.c.aivencloud.com:22784/quanlydiem?ssl-mode=REQUIRED",
            "avnadmin", "AVNS_tvvJpWj2LldY7V1XllZ"
        );
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student")) {
            while (rs.next()) {
                Student s = new Student(
                    rs.getString("msv"),
                    rs.getString("fullname"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("dob"),
                    rs.getString("khoa"),
                    rs.getString("classname")
                );
                students.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // Thêm sinh viên
    public void addStudent(Student s) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO student (msv, fullname, gender, email, dob, khoa, classname) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, s.getMsv());
            ps.setString(2, s.getFullname());
            ps.setString(3, s.getGender());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getDob());
            ps.setString(6, s.getKhoa());
            ps.setString(7, s.getClassname());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Lấy sinh viên theo mã
    public Student getStudentById(String msv) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE msv = ?")) {
            ps.setString(1, msv);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getString("msv"),
                    rs.getString("fullname"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("dob"),
                    rs.getString("khoa"),
                    rs.getString("classname")
                );
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    // Sửa sinh viên
    public void updateStudent(Student s) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "UPDATE student SET fullname=?, gender=?, email=?, dob=?, khoa=?, classname=? WHERE msv=?")) {
            ps.setString(1, s.getFullname());
            ps.setString(2, s.getGender());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getDob());
            ps.setString(5, s.getKhoa());
            ps.setString(6, s.getClassname());
            ps.setString(7, s.getMsv());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Xóa sinh viên
    public void deleteStudent(String msv) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM student WHERE msv=?")) {
            ps.setString(1, msv);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Tìm kiếm sinh viên
    public List<Student> searchStudents(String keyword) {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM student WHERE " +
                "msv LIKE ? OR fullname LIKE ? OR email LIKE ? OR gender LIKE ? OR dob LIKE ? OR khoa LIKE ? OR classname LIKE ?")) {
            String kw = "%" + keyword + "%";
            for (int i = 1; i <= 7; i++) {
                ps.setString(i, kw);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(new Student(
                    rs.getString("msv"),
                    rs.getString("fullname"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("dob"),
                    rs.getString("khoa"),
                    rs.getString("classname")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return students;
    }
}
